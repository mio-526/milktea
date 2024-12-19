<script setup>
import {ref} from "vue";

const iconPrefix = ref("#icon-")
defineProps({
    iconPath:{
      type: String,
      required: true
    },
    width: {
      type: String,
      default: '24'
    },
    height:{
        type: String,
        default: '24'
    }
})
</script>

<template>
  <svg  aria-hidden="true" :width="width" :height="height">
    <use :href="iconPrefix + iconPath" :width="width" :height="height"  />
  </svg>
</template>

<style scoped>

</style>