<script setup>
import {computed} from "vue";
import { createOrder } from '@/api/api.js'
import {ElMessage} from "element-plus";
import { useRouter } from "vue-router";

const router = useRouter();
const shopList = JSON.parse(localStorage.getItem("productList"))
const baseUrl = import.meta.env.VITE_BASE_URL
const total = computed(()=>{
  return shopList.reduce((num,item)=>num+(item.price * item.num),0)
})

const handlePay = () => {
  createOrder(shopList).then(res=>{
    if(res.code === 200){
      ElMessage.success("支付成功！")
      router.push('/order')
    }
  })
}




</script>

<template>
  <div style="display: flex;justify-content: center;align-items: center">
    <el-card style="width: 80%;margin-top: 60px">
      <template #header>
        <div class="card-header">
          <span>购买详情</span>
        </div>
      </template>

      <div>
        <div v-for="item in shopList" :key="item.id" style="margin-top:30px;display: flex;justify-content: space-between;align-items: center">
          <el-image :src="baseUrl + item.imgUrl" style="width: 80px;height: 80px" />
          <div>
            <div>名称： {{ item.name }}</div>
            <div style="margin-top: 10px">单价： {{ item.price }}￥</div>
          </div>
          <div>
            X {{ item.num }}
          </div>
        </div>
        <div style="margin-top: 30px;width: 100%;display: flex;justify-content: right;">
          <div>
            <div>
              <span style="font-size: 16px;color: #000000;font-weight: bold;">总计：</span> {{ total }} ￥
            </div>
            <div style="margin-top: 20px">
              <el-button type="primary" @click="handlePay">支付</el-button>
            </div>
          </div>
        </div>
        <div>

        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>

</style>
