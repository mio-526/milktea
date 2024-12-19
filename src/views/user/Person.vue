<script setup>
import { edit } from "@/api/user/index.js";
import {computed, ref} from "vue";
import {ElMessage} from "element-plus";

const user = ref({})

const getUser = () => {
  user.value = JSON.parse(localStorage.getItem('user'))
}

const submit = () => {
  edit(user.value).then(res=>{
    if(res.code === 200){
      ElMessage.success("修改成功！")
      localStorage.removeItem('user')
      localStorage.setItem('user',JSON.stringify(user.value))
    }
  })
}

getUser()
</script>

<template>
  <div style="display: flex;justify-content: center;align-items: center">
    <el-card style="width: 80%;margin-top: 60px">
      <template #header>
        <div class="card-header">
          <span>基本信息</span>
        </div>
      </template>
      <el-form :model="user">
        <el-form-item label="姓名">
          <el-input v-model="user.userName" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="user.email" />
        </el-form-item>
      </el-form>
      <div style="display: flex;justify-content: center">
        <el-button style="width: 300px" type="primary" @click="submit">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<style scoped>

</style>
