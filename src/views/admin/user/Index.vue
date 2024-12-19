<script setup>
import { list,del } from "@/api/user/index.js";
import { restPassword } from "@/api/api.js";
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
const userList = ref([])
const ids = ref([])
const total = ref(0)
const roleList = ref({
  1: '管理员',
  2: '用户'
})

const data = reactive({
  queryParams: {
    pageSize: 10,
    pageNum: 1,
    userName: null,
    email: null
  }
})

const { queryParams } = toRefs(data)

const getList = () => {
  loading.value = true
  list(data.queryParams).then(res => {
    userList.value = res.data
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
    userName: null,
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
  router.push('/admin/addAndEdit/user')
}

const handleEdit = (row) => {
  router.push({path:'/admin/addAndEdit/user',query:{id:row.id}})
}

const handleRestPassword = (row) => {
  ElMessageBox.prompt('请输入新密码', '修改密码', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
  }).then(({ value }) => {
        return restPassword({id: row.id,password: value })
  }).then(res=>{
      if(res.code === 200){
        ElMessage({
          type: 'success',
          message: '重置成功！',
        })
      }
  }).catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消！',
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
    <div>用户管理</div>
    <el-form :model="queryParams" ref="queryFromRef" label-width="auto" :inline="true"  style="margin-top: 20px">
      <el-form-item label="用户名">
        <el-input v-model="queryParams.userName" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="queryParams.email" placeholder="请输入邮箱" />
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="queryParams.role" placeholder="请选择角色" style="width: 200px">
          <el-option v-for="(value,key) in roleList" :key="key" :label="value" :value="key" />
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
    <el-table :data="userList" ref="tableRef" style="margin-top: 20px;" row-key="id" v-loading="loading" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column label="用户名" prop="userName" align="center" />
      <el-table-column label="邮箱" prop="email" align="center" />
      <el-table-column label="角色"  align="center">
        <template #default="scope">
          <span>{{ roleList[scope.row.role] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" align="center" />
      <el-table-column label="操作" align="center"  >
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="warning" link :icon="Edit">修改</el-button>
          <el-button @click="handleRestPassword(scope.row)" type="warning" link :icon="Edit">重置密码</el-button>
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
