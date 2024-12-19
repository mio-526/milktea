<script setup>
import Header from "@/views/user/layout/Header.vue";
</script>

<template>
  <div class="div_body">
    <div class="div_header">
      <Header />
    </div>
    <div class="div_container">
      <router-view />
    </div>
  </div>
</template>

<style scoped>
.div_body{
  width: 100%;
}
.div_header{
  height: 100px;
}
.div_container{
  width: 100%;

}
</style>
