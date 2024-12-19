<script setup>

import Aside from "./Aside.vue"
</script>

<template>
  <div class="body">
    <el-container>
      <el-aside width="200px">
        <Aside />
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>



</template>

<style scoped>
.body{
  width: 100%;
  height: 100vh;
  background: #F4F6FA;
}
.header{
  margin-top: 15px;
}
.main{
  width: 95%;
  height: 80vh;
  margin-left: 30px;

}
</style>
