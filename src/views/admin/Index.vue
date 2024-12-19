<script setup>
import * as echarts from 'echarts'
import {onMounted, ref} from "vue";
import { daySalesNum,fiveDaysOrderNum,inventoryConsumptionNum  } from '@/api/api.js'

const foldingLineEcharts = ref(null)
const columnarEcharts = ref(null)
const echartsFoldingLine = ref(null)
const echartsColumnar = ref(null)
const productNum = ref(0)
const columnarOption = ref({
  xAxis: {
    type: 'category',
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [],
      type: 'bar'
    }
  ]
})
const foldingLineOption = ref({
  title: {
    text: 'Stacked Line'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: []
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  toolbox: {
    feature: {
      saveAsImage: {}
    }
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: ['9:00', '12:00', '15:00', '18:00', '21:00']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: 'Email',
      type: 'line',
      stack: 'Total',
      data: [120, 132, 101, 134, 90, 230, 210]
    },
    {
      name: 'Union Ads',
      type: 'line',
      stack: 'Total',
      data: [220, 182, 191, 234, 290, 330, 310]
    },
    {
      name: 'Video Ads',
      type: 'line',
      stack: 'Total',
      data: [150, 232, 201, 154, 190, 330, 410]
    },
    {
      name: 'Direct',
      type: 'line',
      stack: 'Total',
      data: [320, 332, 301, 334, 390, 330, 320]
    },
    {
      name: 'Search Engine',
      type: 'line',
      stack: 'Total',
      data: [820, 932, 901, 934, 1290, 1330, 1320]
    }
  ]
})

const getDaySalesNum = () => {
  daySalesNum().then(res=>{
    productNum.value = res.data
  })
}

// 柱状数据
const getFiveDaysOrderNum = () => {
  fiveDaysOrderNum().then(res=>{
    if(res.code === 200){
      let data = res.data
      for(let key in data){
        if(data.hasOwnProperty(key)){
          columnarOption.value.xAxis.data.push(key)
          columnarOption.value.series[0].data.push(data[key])
        }
      }
    }
    echartsColumnar.value.setOption(columnarOption.value)
  })
}

// 折线数据
const getInventoryConsumptionNum = () => {
  inventoryConsumptionNum().then(res=>{
    if(res.code === 200){
      let data = res.data
      let arr = []
      for(let key in data){
        if(data.hasOwnProperty(key)){
          arr.push({
            name: key,
            type: 'line',
            stack: 'Total',
            data: data[key]
          })
          foldingLineOption.value.legend.data.push(key)
        }
      }
      foldingLineOption.value.series = arr
    }
    echartsFoldingLine.value.setOption(foldingLineOption.value)
  })
}



onMounted(()=>{
  echartsFoldingLine.value = echarts.init(foldingLineEcharts.value)
  echartsColumnar.value = echarts.init(columnarEcharts.value)
  getFiveDaysOrderNum()
  getInventoryConsumptionNum()
})


getDaySalesNum()
</script>

<template>
  <div class="div_body">
    <div style="margin-top: 70px">欢迎使用后台管理系统</div>

    <div style="margin-top: 90px">
      奶茶总数：{{ productNum }}
    </div>

    <div style="width: 100%;height: 40vh;display: flex;margin-top: 120px">
      <div ref="foldingLineEcharts" class="echarts"></div>
      <div ref="columnarEcharts" class="echarts"></div>
    </div>
  </div>
</template>

<style scoped>
.echarts{
  width: 100%;
  height: 40vh;
}
.div_body{
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  color: #000000;
  flex-direction: column;
}
</style>
