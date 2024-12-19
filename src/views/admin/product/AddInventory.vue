<script setup>
import {reactive, ref, toRefs} from "vue";
import { useRoute,useRouter } from "vue-router";
import { getProductInventory } from '@/api/product/index.js'
import { addBatch } from '@/api/productInventory/index.js'
import {
  Back
} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";
import { list as listInventory } from "@/api/inventory/index.js";

const route = useRoute()
const router = useRouter()
const id = route.query.id
const inventoryList = ref([])
const productInventoryList = ref([])



const handleBack = () => {
  router.back()
}

const getById = () => {
  if(id){
    getProductInventory(id).then(res=>{
      if(res.code === 200){
        productInventoryList.value = res.data.productInventoryList
      }
    })
  }
}

const handleAdd = () => {
  productInventoryList.value.push({productId: id, inventoryId: null, num: 1})
}

const handleDel = (index) => {
  productInventoryList.value.splice(index, 1)
}

const submit = () => {
  addBatch(productInventoryList.value).then(res => {
        if(res.code === 200){
          ElMessage({
            type: 'success',
            message: '新增成功！'
          })
          handleBack()
        }
      })

}

const getInventoryList = () => {
  listInventory().then(res=>{
    if(res.code === 200){
      inventoryList.value = res.data
    }
  })
}

getById()
getInventoryList()
</script>

<template>
  <div>
    <div class="header">
      <el-button type="primary" :icon="Back" link @click="handleBack">返回</el-button>
      <div>配置原料</div>
    </div>
    <div class="form">
      <el-table :data="productInventoryList">
        <el-table-column label="原料名称">
          <template #default="scope">
            <el-select v-model="scope.row.inventoryId" placeholder="请选择原料名称">
              <el-option v-for="item in inventoryList" :key="item.id" :value="item.id" :label="item.name" />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="数量">
          <template #default="scope">
            <el-input-number v-model="scope.row.num" min="1" step="1"  />
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button link type="danger" @click="handleDel(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button style="width: 100%;margin-top: 15px" @click="handleAdd">添加</el-button>
      <div style="display: flex;justify-content: center;margin-top: 15px">
        <el-button @click="submit" type="primary">确定</el-button>
        <el-button @click="handleBack">取消</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.header{
  display: flex;
}
.header div{
  margin-left: 20px;
  font-size: 14px;
}
.form{
  margin-top: 40px;
  border: 1px solid #999999;
  border-radius: 25px;
  padding: 20px;
}
</style>
