<script setup>
import {reactive, ref, toRefs} from "vue";
import { useRoute,useRouter } from "vue-router";
import {add, edit, get} from '@/api/employee/index.js'
import {
  Back
} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";

const route = useRoute()
const router = useRouter()
const id = route.query.id
const title = ref("")
const formRef = ref(null)


const salaryValidator = (rule,value,callback) => {
  const regex = /^[0-9]*\.?[0-9]*$/;
  if(regex.test(value)){
    callback()
  }else{
    callback(new Error('请输入数字'))
  }
}



const data = reactive({
  form: {},
  rule: {
    name:[{required: true, message: '姓名不能为空',trigger: 'blur'}],
    position:[{required: true, message: '职位不能为空',trigger: 'blur'}],
    phone:[{required: true, message: '手机号不能为空',trigger: 'blur'}],
    hireTime:[{required: true, message: '入职时间不能为空',trigger: 'blur'}],
    salary:[{required: true, validator: salaryValidator,trigger: 'change'}],
  }
})

const { form, rule } = toRefs(data)

const handleBack = () => {
  router.back()
}

const getById = () => {
  if(id){
    title.value = "修改员工"
    get(id).then(res=>{
      if(res.code === 200){
        form.value = res.data
      }
    })
  }else{
    title.value = "新增员工"
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
        <el-form-item label="姓名:" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="职位:" prop="position">
          <el-input v-model="form.position" placeholder="请输入职位" />
        </el-form-item>
        <el-form-item label="手机号:" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号"  />
        </el-form-item>
        <el-form-item label="工资:" prop="salary">
          <el-input v-model="form.salary" placeholder="请输入工资"  />
        </el-form-item>
        <el-form-item label="入职时间:" prop="hireTime">
          <el-date-picker
              v-model="form.hireTime"
              type="date"
              placeholder="请选择入职时间"
              value-format="YYYY-MM-DD"
          />
        </el-form-item>
<!--        <el-form-item label="状态:" prop="status">-->
<!--          <el-select v-model="form.status" placeholder="请选择角色">-->
<!--            <el-option v-for="(value,key) in roleList" :key="key" :value="parseInt(key)"  :label="value" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
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
