<script setup>
import { useRouter } from "vue-router";
import {
  ArrowDown
} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox} from "element-plus";

const router = useRouter()

const handleBack = () => {
  ElMessageBox.confirm('是否退出管理端！','提示',{
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(()=>{
    router.push('/')
  }).catch(()=>{
    ElMessage.info('已取消')
  })
}
</script>

<template>
  <div class="div_menu">
    <el-row>
      <el-col>
        <div class="div_title">后台管理</div>
        <el-menu class="el-menu-demo" router background-color="#FDF5DC">
          <el-menu-item index="/admin">
            <span>首页</span>
          </el-menu-item>
          <el-menu-item index="/admin/user">用户管理</el-menu-item>
          <el-sub-menu>
            <template #title>
              产品管理
            </template>
            <el-menu-item  index="/admin/category">类别管理</el-menu-item>
            <el-menu-item  index="/admin/product">奶茶管理</el-menu-item>
          </el-sub-menu>
          <el-menu-item  index="/admin/inventory">库存管理</el-menu-item>
          <el-menu-item  index="/admin/order">订单管理</el-menu-item>
          <el-menu-item  index="/admin/employee">员工管理</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <div style="position: absolute;bottom: 30px;">
      <el-button @click="handleBack" link>退出管理</el-button>
    </div>
  </div>
</template>

<style scoped>
.div_menu{
  width: 99%;
  height:100vh;
  border-right: 2px solid #e89c29;
  background: #FDF5DC;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.div_title{
  margin-top: 30px;
  font-size: 20px;
  color: #000000;
  background: #FFDC59;
  border: 2px solid #000000;
  width: 80%;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-menu{
  border-right:transparent !important;
}

.el-menu-demo{
  width: 100%;
  margin-top: 30px;
}




</style>
