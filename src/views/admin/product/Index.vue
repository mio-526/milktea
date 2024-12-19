<script setup>
import { list,del } from "@/api/product/index.js";
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
import { list as listCategory } from "@/api/category/index.js";

const baseUrl = import.meta.env.VITE_BASE_URL
const router = useRouter()
const loading = ref(false)
const queryFromRef = ref(null)
const tableRef = ref(null)
const productList = ref([])
const ids = ref([])
const total = ref(0)
const categoryList = ref([])

const data = reactive({
  queryParams: {
    pageSize: 10,
    pageNum: 1,
    name: null,
    categoryId: null
  }
})

const { queryParams } = toRefs(data)

const getList = () => {
  loading.value = true
  list(data.queryParams).then(res => {
    productList.value = res.data
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
    name: null,
    categoryId: null
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
  router.push('/admin/addAndEdit/product')
}

const handleEdit = (row) => {
  router.push({path:'/admin/addAndEdit/product',query:{id:row.id}})
}

const handleInventory = (row) => {
  router.push({path:'/admin/addInventory',query:{id:row.id}})
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

const getCategoryList = () => {
  listCategory().then(res=>{
    if(res.code === 200){
      categoryList.value = res.data
    }
  })
}


getList()
getCategoryList()
</script>

<template>
  <div>
    <div>用户管理</div>
    <el-form :model="queryParams" ref="queryFromRef" label-width="auto" :inline="true"  style="margin-top: 20px">
      <el-form-item label="名称">
        <el-input v-model="queryParams.name" placeholder="请输入名称" />
      </el-form-item>

      <el-form-item label="类别">
        <el-select v-model="queryParams.categoryId" placeholder="请选择类别" style="width: 200px;">
          <el-option v-for="item in  categoryList" :key="item.id" :value="item.id" :label="item.name" />
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
    <el-table :data="productList" ref="tableRef" style="margin-top: 20px;" row-key="id" v-loading="loading" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column label="名称" prop="name" align="center" />
      <el-table-column label="单价/元" prop="price" align="center" />
      <el-table-column label="描述" prop="description" align="center" />
      <el-table-column label="图片" prop="imgUrl" align="center">
        <template #default="scope">
          <el-image :src="baseUrl + scope.row.imgUrl" style="width: 50px;height: 50px" />
        </template>
      </el-table-column>
      <el-table-column label="类别"  align="center" prop="categoryName" />
      <el-table-column label="创建时间" prop="createTime" align="center" />
      <el-table-column label="操作" align="center"  >
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="warning" link :icon="Edit">修改</el-button>
          <el-button @click="handleInventory(scope.row)" type="warning" link :icon="Edit">配置原料</el-button>
          <el-button @click="handleDel(scope.row)" type="danger" link :icon="Delete" >删除</el-button>
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
