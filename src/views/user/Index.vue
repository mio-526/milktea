<script setup>
import {list as listCategory} from "@/api/category/index.js";
import { list as listProduct } from "@/api/product/index.js";
import {ref} from "vue";
import {
  Search,
  Plus,
  ShoppingBag
} from '@element-plus/icons-vue'
import { useRouter } from "vue-router";
import {ElMessage} from "element-plus";

const router = useRouter()
const baseUrl = import.meta.env.VITE_BASE_URL
const categoryList = ref([])
const productList = ref([])
const openShop = ref(false)
const product = ref({
  categoryId: null,
  name: null
})
const category = ref({
  name: "全部"
})

const shopList = ref([])

const getCategoryList = ()  => {
  listCategory().then(res=>{
    categoryList.value = res.data
  })
}

const getProductList = () => {
  listProduct(product.value).then(res=>{
    productList.value = res.data
  })
}

const handleCategoryChange = (item) => {
  category.value = item
  product.value.categoryId = item.id
  getProductList()
}

const handleAddShop = (item) => {
  let obj = {
    productId: item.id,
    imgUrl: item.imgUrl,
    num: item.num === undefined ? 1 : item.num,
    name: item.name,
    price: item.price
  }
  for(let i = 0; i < shopList.value.length; i++){
    if(shopList.value[i].productId === obj.productId){
      shopList.value[i].num += 1
      return
    }
  }
  shopList.value.push(obj)
}

const handlePay = () => {
  if(shopList.value.length < 1){
    ElMessage.warning("商品不能为空！")
    return
  }
  localStorage.setItem("productList",JSON.stringify(shopList.value))
  router.push("/pay")
}

const handleDel = (index) => {
  shopList.value.splice(index,1)
}

getProductList()
getCategoryList()
</script>

<template>
  <div >
    <el-row :gutter="60" style="margin-right: 0;">
      <el-col :span="5" class="col_style">
        <li class="li_category_list">
          <ul @click="handleCategoryChange({name: '全部',id: null})">全部</ul>
          <ul v-for="item in categoryList" :key="item.id" @click="handleCategoryChange(item)">{{ item.name }}</ul>
        </li>
      </el-col>
      <el-col :span="17" class="col_style">
         <div class="div_categoryName">{{ category.name }}</div>

         <el-input style="margin-top: 15px" v-model="product.name">
           <template #append>
             <el-button :icon="Search" @click="getProductList" />
           </template>
         </el-input>

         <div v-for="item in productList" :key="item.id" class="div_product_item">
           <div>
             <el-image :src="baseUrl + item.imgUrl" style="width: 100px;height: 100px"   />
           </div>
           <div>
             <div>名称： {{ item.name }}</div>
             <div style="margin-top: 12px">描述： {{ item.description }}</div>
           </div>
           <div>
             <div>价格： ￥{{ item.price }}</div>
             <el-button style="margin-top: 12px" circle size="large" :icon="Plus" color="#e89c29" @click="handleAddShop(item)"></el-button>
           </div>
         </div>
      </el-col>
    </el-row>

    <div @click="openShop = true" class="div_shop">
      <div>
        <el-badge :value="shopList.length" style="height: 50px;width: auto">
          <el-icon size="50px"><ShoppingBag /></el-icon>
        </el-badge>
        <el-button style="margin-right: 20px" round color="#f46a08" size="large" @click.stop="handlePay">购买</el-button>
      </div>
    </div>

    <el-drawer
        v-model="openShop"
        title="购物车"
        direction="btt"
    >
      <el-table :data="shopList">
        <el-table-column type="index" align="center" label="编号" width="60px" />
        <el-table-column align="center" label="名称" prop="name" />
        <el-table-column align="center" label="数量" prop="num" />
        <el-table-column align="center" label="操作" >
          <template #default="scope">
            <el-button link type="danger"  @click="handleDel(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
  </div>
</template>

<style scoped>
.li_category_list{
  margin-left: 10px;
  list-style: none;
  width: 100%;
}
.li_category_list ul{
  margin-top: 2px;
  height: 50px;
  background: #e89c29;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 25px;
  border: 1px solid #e89c29;
}
.li_category_list ul:hover{
  cursor: pointer;
  background: #ffffff;
}
.div_categoryName{
  font-size: 24px;
  color: black;
  font-weight: bold;
}
.div_product_item{
  display: flex;
  justify-content: space-between;
  margin-top: 25px;
  align-items: center;
}
.div_shop{
  width: 100%;
  position: fixed;
  bottom: 100px;
  display: flex;
  justify-content: center;
}
.div_shop div{
  background: #e89c29;
  padding-left: 20px;
  width:70%;
  height: 80px;
  border-radius: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.div_shop:hover{
  cursor: pointer;
}

.col_style{
  height: 620px;
  overflow: scroll;
}

.col_style::-webkit-scrollbar {
  width: 6px;
  height: 0;
}

.col_style::-webkit-scrollbar-thumb {
  background-color: #888;
  border-radius: 10px;
}

.col_style::-webkit-scrollbar-thumb:hover {
  background-color: #555;
}
</style>
