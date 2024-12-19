<script setup>
import { list,del,edit } from "@/api/employee/index.js";
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
const employeeList = ref([])
const ids = ref([])
const total = ref(0)
const status = ref({
  1: '在职',
  2: '离职'
})

const data = reactive({
  queryParams: {
    pageSize: 10,
    pageNum: 1,
    employeeName: null,
    email: null
  }
})

const { queryParams } = toRefs(data)

const getList = () => {
  loading.value = true
  list(data.queryParams).then(res => {
    employeeList.value = res.data
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
    employeeName: null,
    email: null
  }
  ids.value = []
}

const handleReset = () => {
  reset()
  getList()
}

const handleQuery = () => {
  getList()
}

const handleSelectionChange = (row) => {
  ids.value = row.map(item => item.id)
}

const handleAdd = () => {
  router.push('/admin/addAndEdit/employee')
}

const handleEdit = (row) => {
  router.push({path:'/admin/addAndEdit/employee',query:{id:row.id}})
}

const handleStatusChange = (row) => {
  let text = row.status === 1 ? '入职' : '离职'
  ElMessageBox.confirm(
      `是否${text}当前姓名为${row.name}员工`,
      '提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(()=>{
    return edit({id:row.id,status:row.status})
  }).then((res)=>{
    if(res.code === 200){
      ElMessage({
        type: 'success',
        message: `${text}成功！`
      })
      getList()
    }
  }).catch(()=>{
    row.status = row.status === 1 ? 2 : 1
    ElMessage({
      type: 'info',
      message: '取消！'
    })
  })
}

const handleDel = (row) => {
  const id = row.id || ids.value
  ElMessageBox.confirm(
      '确定要删除当前数据？',
      '提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(()=>{
    return del(id)
  }).then(res=>{
      if(res.code === 200){
        ElMessage({
          type: 'success',
          message: '删除成功！'
        })
        getList()
      }
  }).catch(()=>{
    ElMessage({
      type: 'info',
      message: '已取消！'
    })
  })
}


getList()
</script>

<template>
  <div>
    <div>员工管理</div>
    <el-form :model="queryParams" ref="queryFromRef" label-width="auto" :inline="true"  style="margin-top: 20px">
      <el-form-item label="姓名">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="职位">
        <el-input v-model="queryParams.position" placeholder="请输入职位" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.role" placeholder="请选择状态" style="width: 200px">
          <el-option v-for="(value,key) in status" :key="key" :label="value" :value="key" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" type="primary" :icon="Search">搜索</el-button>
        <el-button @click="handleReset" color="#F6F6F6" :icon="Refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-button @click="handleAdd" type="primary" :icon="Plus">新增</el-button>
      <el-button @click="handleDel" :disabled="ids.length <= 0" type="danger" :icon="Delete">删除</el-button>
    </div>
    <el-table :data="employeeList" ref="tableRef" style="margin-top: 20px;" row-key="id" v-loading="loading" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column label="姓名" prop="name" align="center" />
      <el-table-column label="职位" prop="position" align="center" />
      <el-table-column label="手机号" prop="phone" align="center" />
      <el-table-column label="状态"  align="center">
        <template #default="scope">
          <el-switch
              v-model="scope.row.status"
              active-text="在职"
              inactive-text="离职"
              :inactive-value="2"
              :active-value="1"
              @change="handleStatusChange(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column label="工资" prop="salary" align="center" />
      <el-table-column label="入职时间" prop="hireTime" align="center" />
      <el-table-column label="操作" align="center"  >
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="warning" link :icon="Edit">修改</el-button>
          <el-button  type="danger" link :icon="Delete" @click="handleDel(scope.row)">删除</el-button>
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
