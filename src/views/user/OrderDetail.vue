<script setup>
import { orderByOrderNo,cancelOrder } from '@/api/api.js'
import {ref} from "vue";
import {useRoute,useRouter} from "vue-router";
import {Back} from "@element-plus/icons-vue";

import {ElMessage, ElMessageBox} from "element-plus";

const route = useRoute()
const router = useRouter()
const orderNo = route.query.orderNo
const baseUrl = import.meta.env.VITE_BASE_URL
const order = ref({})
const status = ref({
  1: '制作中',
  2: '已完成',
  3: '已取消'
})

const handleBack = () => {
  router.back()
}

const getOrderByOrderNo = () => {
  orderByOrderNo(orderNo).then(res=>{
    order.value = res.data
  })
}

const handleCancel = () => {
  ElMessageBox.confirm('是否取消当前订单','提示',{
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(()=>{
    return cancelOrder(order.value)
  }).then(res=>{
    if(res.code === 200){
      ElMessage.success("取消成功！")
      handleBack()
    }
  }).catch(()=>{
    ElMessage.info("已取消操作！")
  })
}



getOrderByOrderNo()
</script>

<template>
  <div style="display: flex;justify-content: center;align-items: center">
    <el-card style="width: 80%;margin-top: 60px">
      <template #header>
        <div class="card-header">
          <div><el-button link type="primary" :icon="Back" @click="handleBack">返回</el-button> | <span>订单详情</span></div>
          <div>
            <el-button v-if="order.status !== 3" type="warning" @click="handleCancel">取消</el-button>
          </div>
        </div>
      </template>
      <div>
        <div   class="div_order_item" >
          <div class="div_order_title">
            <div><span class="span_text">订单编号：</span> {{ order.orderNo }}</div>
            <div style="color: #000000;font-size: 18px;font-weight: bold">{{ status[order.status] }}</div>
          </div>

          <div style="display: flex;margin-top: 30px">
            <div class="span_text" style="width: 200px">订单详情：</div>
            <div  class="div_order_detail_item">
              <div v-for="orderDetail in order.orderDetailViewList" style="display: flex;align-items: center;margin-left:20px ">
                <div style="display: flex;flex-direction: column;align-items: center">
                  <div><el-image :src="baseUrl + orderDetail.productImgUrl" style="width: 100px;height: 100px" /></div>
                  <div><span>{{ orderDetail.productName }}</span></div>
                </div>
                <div style="margin-left: 20px">
                  X <span class="span_text">{{ orderDetail.num }}</span>
                </div>
              </div>
            </div>
          </div>
          <div>
            <span class="span_text">总计：{{ order.totalPrice }}￥</span>
          </div>

        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.div_order_item{
  border: 1px solid #999999;
  border-radius: 30px;
  padding: 30px;
}
.div_order_item:hover{
  cursor: pointer;
}
.div_order_title{
  display: flex;
  justify-content: space-between;
}
.span_text{
  color: #000000;
  font-size: 18px;
  font-weight: bold;
}

.card-header{
  display: flex;
  justify-content: space-between;
}

.div_order_detail_item{
  display: flex;
  overflow: scroll;
}

.div_order_detail_item::-webkit-scrollbar {
  width: 0;
  height: 12px;
}

.div_order_detail_item::-webkit-scrollbar-thumb {
  background-color: #888;
  border-radius: 10px;
}

.div_order_detail_item::-webkit-scrollbar-thumb:hover {
  background-color: #555;
}
</style>
