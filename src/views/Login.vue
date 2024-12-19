<script setup>
import { login } from "@/api/login.js";
import {reactive, ref, toRefs} from "vue";
import { useRouter } from "vue-router";
import {ElMessage} from "element-plus";

const router = useRouter()

const formRef = ref(null)

const data = reactive({
  form: {
    userName: null,
    password: null
  },
  rule:{
    userName: [{required:true,message: '账号不能为空',trigger: 'blur'}],
    password: [{required:true,message: '密码不能为空',trigger: 'blur'}]
  }
})

const { form,rule } = toRefs(data)

const submitRegister = () => {
  router.push({path: '/register'})
}

const submitLogin = () => {
  formRef.value.validate((validated) => {
    if(validated){
      login(data.form).then(res => {
        localStorage.setItem("token", res.token)
        ElMessage.success('登录成功！')
        router.push({path: '/'})
      })
    }
  })
}

</script>

<template>
  <div class="div_body">
    <div class="div_login">
      <div class="div_title">
        奶茶管理系统
      </div>
      <div class="div_login_form">
        <el-form ref="formRef" :model="form" :label-width="80" :rules="rule">
          <el-form-item prop="userName">
            <template #label>
              <span style="color: #000000;font-size: 16px">账号:</span>
            </template>
            <el-input v-model="form.userName" placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item prop="password">
            <template #label>
              <span style="color: #000000;font-size: 16px">密码:</span>
            </template>
            <el-input v-model="form.password" placeholder="请输入密码" show-password />
          </el-form-item>
        </el-form>
      </div>
      <div class="div_btn">
        <el-button @click="submitLogin">登录</el-button>
        <el-button @click="submitRegister">注册</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped>

.div_body{
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  background-image: url("../assets/image/login.jpg");
}
.div_login{
  width: 20%;
  height: auto;
  position: relative;
  left: 360px;
  background-color: rgba(118, 103, 198, 0.2); /* 半透明背景 */
  backdrop-filter: blur(10px); /* 背景模糊 */
  border-radius: 20px;
  box-shadow: 1px 1px 40px #98a9af;
  padding: 5%;
}
.div_title{
  font-size: 20px;
  color: #000000;
  font-weight: bold;
  display: flex;
  justify-content: center;
}
.div_login_form{
  margin-top: 30px;
}

.div_btn{
  display: flex;
  justify-content: center;
}
</style>
