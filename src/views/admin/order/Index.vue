<script setup>
import { list,success } from "@/api/order/index.js";
import {reactive, toRefs, ref} from "vue";
import {
  Delete,
  Edit,
  Search,
  Refresh,
  Plus
} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox} from "element-plus";
import {useRouter} from "vue-router";


const router = useRouter()
const loading = ref(false)
const queryFromRef = ref(null)
const tableRef = ref(null)
const orderList = ref([])
const total = ref(0)
const status = ref({
  1: '制作中',
  2: '完成',
  3: '取消'
})

const data = reactive({
  queryParams: {
    pageSize: 10,
    pageNum: 1,
    orderNo: null,
    status: null
  }
})

const { queryParams } = toRefs(data)

const getList = () => {
  loading.value = true
  list(data.queryParams).then(res => {
    orderList.value = res.data
    total.value = res.total
    loading.value = false
  })
}

const reset = () => {
  if(queryFromRef.value !== null)
    queryFromRef.value.resetFields()
  queryParams.value = {
    pageSize: 10,
    pageNum: 1,
    orderNo: null,
    status: null
  }
}

const handleReset = () => {
  reset()
  getList()
}

const handleQuery = () => {
  getList()
}

const handleDetail = (row) => {
  router.push({path:'/admin/orderDetail',query:{orderNo:row.orderNo}})
}

const handleSuccess = (row) => {
  success(row).then(res=>{
    if(res.code === 200) {
      ElMessage.success("操作成功！")
      getList()
    }
  })
}


getList()
</script>

<template>
  <div>
    <div>订单管理</div>
    <el-form :model="queryParams" ref="queryFromRef" label-width="auto" :inline="true"  style="margin-top: 20px">
      <el-form-item label="订单编号">
        <el-input v-model="queryParams.orderNo" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" placeholder="请选择状态" style="width: 200px">
          <el-option v-for="(value,key) in status" :key="key" :label="value" :value="key" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" type="primary" :icon="Search">搜索</el-button>
        <el-button @click="handleReset" color="#F6F6F6" :icon="Refresh">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="orderList" ref="tableRef" style="margin-top: 20px;" row-key="id" v-loading="loading">
      <el-table-column label="订单编号" prop="orderNo" align="center" />
      <el-table-column label="用户名" prop="userName" align="center" />
      <el-table-column label="总价" prop="totalPrice"  align="center" />
      <el-table-column label="状态" prop="status"  align="center">
        <template #default="scope">
          <span>{{ status[scope.row.status] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" align="center" />
      <el-table-column label="操作" align="center"  >
        <template #default="scope">
          <el-button @click="handleDetail(scope.row)" type="warning" link :icon="Edit">查看详情</el-button>
          <el-button v-show="scope.row.status === 1" @click="handleSuccess(scope.row)" type="warning" link :icon="Edit">完成</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="display: flex;justify-content: right;margin-top: 30px">
      <pagination
          v-show="total > 0"
          :total="total"
          v-model:current="queryParams.pageNum"
          v-model:size="queryParams.pageSize"
          @pagination="getList"
      />
    </div>
  </div>
</template>

<style scoped>

</style>
