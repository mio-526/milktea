<script setup>
import { ref} from "vue";
import {useRouter} from "vue-router";
import {getInfo, restPassword} from "@/api/api.js"
import { Tickets } from "@element-plus/icons-vue"
import {ElMessage, ElMessageBox} from "element-plus";

const router = useRouter();

const user = ref({})

const getUser = () => {
  user.value = JSON.parse(localStorage.getItem("user"));
  if(user.value === null){
    getInfo().then(res=>{
      user.value = res.data
    })
  }
}

const handleHome = () => {
  router.push('/')
}

const handleToPerson = () => {
  router.push('/person')
}

const handleToBackstage = () => {
  router.push('/admin')
}

const handleLayout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
  localStorage.removeItem('productList')
  router.push('/login')
}

const handleOrder = () => {
  router.push('/order')
}

const handleRestPassword = () => {
  ElMessageBox.prompt('请输入新密码', '修改密码', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
  }).then(({ value }) => {
    return restPassword({id: user.value.id ,password: value })
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

getUser()
</script>

<template>
  <div class="div_header">

    <div style="margin-left: 30px" @click="handleHome">
      <span class="span_title">欢迎选购奶茶</span>
    </div>

    <div style="display: flex;margin-right: 30px">
      <div style="margin-right: 30px">
        <el-button link type="primary" :icon="Tickets" @click="handleOrder">订单管理</el-button>
      </div>

      <div v-if="user === null">
        <el-button link>登录</el-button> | <el-button link style="margin-left: 0">注册</el-button>
      </div>
      <div v-else>
        <el-dropdown trigger="click">
          <span class="span_user_name">
            {{ user.userName }}
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="handleToPerson">个人中心</el-dropdown-item>
              <el-dropdown-item @click="handleRestPassword">重置密码</el-dropdown-item>
              <el-dropdown-item v-if="user.role === 1" @click="handleToBackstage">管理</el-dropdown-item>
              <el-dropdown-item @click="handleLayout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
      <div>
        <div>

        </div>
      </div>

    </div>
  </div>
</template>

<style scoped>
.div_header {
  width: 100%;
  height: 80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: fixed;
  top: 0;
  background: #ffffff;
  z-index: 999;
}

.span_user_name {
  margin-top: 4px;
  color: #000000;
  font-size: 16px;
}

.span_title{
  font-size: 30px;
  color: black;
  font-weight: bold;
}

.span_user_name:hover,.span_title:hover{
  cursor: pointer;
}

</style>
