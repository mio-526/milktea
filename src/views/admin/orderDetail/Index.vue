<script setup>
import { list } from "@/api/orderDetail/index.js";
import {reactive, toRefs, ref} from "vue";
import {useRoute} from "vue-router";

const baseUrl = import.meta.env.VITE_BASE_URL
const route = useRoute()
const orderNo = route.query.orderNo
const loading = ref(false)
const queryFromRef = ref(null)
const tableRef = ref(null)
const orderDetailList = ref([])
const total = ref(0)


const data = reactive({
  queryParams: {
    pageSize: 10,
    pageNum: 1,
    orderNo: orderNo
  }
})

const { queryParams } = toRefs(data)

const getList = () => {
  loading.value = true
  list(data.queryParams).then(res => {
    orderDetailList.value = res.data
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
    orderNo: orderNo
  }
}

getList()
</script>

<template>
  <div>
    <div>订单详情管理</div>
    <el-table :data="orderDetailList" ref="tableRef" style="margin-top: 20px;" row-key="id" v-loading="loading">
      <el-table-column label="名称" prop="productName" align="center" />
      <el-table-column label="单价/元" prop="productProduct" align="center" />
      <el-table-column label="数量" prop="num" align="center" />
      <el-table-column label="图片" prop="productImgUrl" align="center">
        <template #default="scope">
          <el-image :src="baseUrl + scope.row.productImgUrl" style="width: 50px;height: 50px" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" align="center" />
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
