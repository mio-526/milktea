<script setup>
import {reactive, ref, toRefs} from "vue";
import { useRoute,useRouter } from "vue-router";
import {add, edit, get} from '@/api/product/index.js'
import {
  Back
} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";
import { list as listCategory } from "@/api/category/index.js";

const baseUrl = import.meta.env.VITE_BASE_URL
const route = useRoute()
const router = useRouter()
const id = route.query.id
const title = ref("")
const formRef = ref(null)
const fileList = ref([])
const categoryList = ref([])

const priceValidator = (rule,value,callback) => {
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
    name: [{required:true,message: '名称不能为空', trigger: 'blur'}],
    price: [{required:true,validator: priceValidator, trigger: 'change'}],
    imgUrl: [{required:true,message: '图片不能为空', trigger: 'change'}],
    categoryId: [{required:true,message: '类别不能为空', trigger: 'blur'}],
  }
})

const { form, rule } = toRefs(data)

const handleBack = () => {
  router.back()
}

const getById = () => {
  if(id){
    title.value = "修改商品"
    get(id).then(res=>{
      if(res.code === 200){
        form.value = res.data
        fileList.value.push({url: baseUrl + res.data.imgUrl})
      }
    })
  }else{
    title.value = "新增商品"
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

const getCategoryList = () => {
  listCategory().then(res=>{
    if(res.code === 200){
      categoryList.value = res.data
    }
  })
}

getById()
getCategoryList()
</script>

<template>
  <div>
    <div class="header">
      <el-button type="primary" :icon="Back" link @click="handleBack">返回</el-button>
      <div>{{ title }}</div>
    </div>
    <div class="form">
      <el-form :model="form" label-width="auto" ref="formRef" :rules="rule">
        <el-form-item label="名称:" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="单价:" prop="price">
          <el-input v-model="form.price" placeholder="请输入单价">
            <template #prefix>
              <span style="color: #000000">￥</span>
            </template>
            <template #suffix>
              <span style="color: #000000">元</span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="描述:" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述"  />
        </el-form-item>
        <el-form-item label="图片:" prop="imgUrl">
          <upload
              v-model:fileList="fileList"
              v-model="form.imgUrl"
          />
        </el-form-item>
        <el-form-item label="类别:" prop="categoryId">
          <el-select v-model="form.categoryId" placeholder="请选择类别">
            <el-option v-for="item in  categoryList" :key="item.id" :value="item.id" :label="item.name" />
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
