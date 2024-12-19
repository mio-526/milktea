<script setup>
import {reactive, ref, toRefs} from "vue";
import { useRoute,useRouter } from "vue-router";
import {add, edit, get} from '@/api/user/index.js'
import {
  Back
} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";

const route = useRoute()
const router = useRouter()
const id = route.query.id
const title = ref("")
const formRef = ref(null)
const roleList = ref({
  1: '管理员',
  2: '用户'
})

const data = reactive({
  form: {},
  rule: {
    userName:[{required: true, message: '用户名不能为空',trigger: 'blur'}],
    password:[{required: true, message: '密码不能为空',trigger: 'blur'}],
    role:[{required: true, message: '角色不能为空',trigger: 'change'}],
  }
})

const { form, rule } = toRefs(data)

const handleBack = () => {
  router.back()
}

const getById = () => {
  if(id){
    title.value = "修改用户"
    get(id).then(res=>{
      if(res.code === 200){
        form.value = res.data
      }
    })
  }else{
    title.value = "新增用户"
  }
}

const submit = () => {
  formRef.value.validate((validated) => {
    if(validated){
      if(form.value.id){
        edit(form.value).then(res => {
          if(res.code === 200){
            ElMessage({
              type: 'success',
              message: '修改成功！'
            })
            handleBack()
          }
        })
      }else{
        add(form.value).then(res => {
          if(res.code === 200){
            ElMessage({
              type: 'success',
              message: '新增成功！'
            })
            handleBack()
          }
        })
      }
    }
  })
}

getById()
</script>

<template>
  <div>
    <div class="header">
      <el-button type="primary" :icon="Back" link @click="handleBack">返回</el-button>
      <div>{{ title }}</div>
    </div>
    <div class="form">
      <el-form :model="form" label-width="auto" ref="formRef" :rules="rule">
        <el-form-item label="用户名:" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码:" prop="password" v-if="form.id == null">
          <el-input  v-model="form.password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item label="邮箱:" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"  />
        </el-form-item>
        <el-form-item label="角色:" prop="role">
          <el-select v-model="form.role" placeholder="请选择角色">
            <el-option v-for="(value,key) in roleList" :key="key" :value="parseInt(key)"  :label="value" />
          </el-select>
        </el-form-item>
      </el-form>
      <div style="display: flex;justify-content: center">
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
