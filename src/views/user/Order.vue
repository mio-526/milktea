<script setup>
import { listOrder } from '@/api/api.js'
import {ref} from "vue";
import {useRouter} from "vue-router";

const router = useRouter()
const baseUrl = import.meta.env.VITE_BASE_URL
const orderList = ref([])
const status = ref({
  1: '制作中',
  2: '已完成',
  3: '已取消'
})

const getOrderList = () => {
  listOrder().then(res=>{
    orderList.value = res.data
  })
}

const handleOrderDetail = (item) => {
  router.push({path: '/orderDetail',query: {orderNo : item.orderNo}})
}

getOrderList()
</script>

<template>
  <div style="display: flex;justify-content: center;align-items: center">
    <el-card style="width: 80%;margin-top: 60px">
      <template #header>
        <div class="card-header">
          <span>订单管理</span>
        </div>
      </template>
      <div>
        <div v-for="item in orderList" :key="item.id" class="div_order_item" @click="handleOrderDetail(item)">
          <div class="div_order_title">
            <div><span class="span_text">订单编号：</span> {{ item.orderNo }}</div>
            <div style="color: #000000;font-size: 18px;font-weight: bold">{{ status[item.status] }}</div>
          </div>

          <div style="display: flex;margin-top: 30px">
            <div class="span_text" style="width: 200px">订单详情：</div>
            <div  class="div_order_detail_item">
              <div v-for="orderDetail in item.orderDetailViewList" style="display: flex;align-items: center;margin-left:20px ">
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
            <span class="span_text">总计：{{ item.totalPrice }}￥</span>
          </div>

        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.div_order_item{
  margin-top: 20px;
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
