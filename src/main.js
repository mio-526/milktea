import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

import router from '@/router/index.js'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import Pagination from '@/components/page/Pagination.vue'
import Upload from '@/components/upload/index.vue'
// import Editor from '@/components/editor/index.vue'
import SvgIcon from '@/components/icon/index.vue'
import store from "@/store/index.js";

const app = createApp(App)

app.component('Pagination',Pagination)
app.component('Upload',Upload)
app.component('SvgIcon',SvgIcon)
// app.component('Editor',Editor)

const whiteList = ["/login","/register"]

router.beforeEach(  (to,from,next)=>{
    const token = localStorage.getItem("token")
    if(token){
        if(whiteList.includes(to.path)){
            next('/')
        }
        // 获取用户信息
        const user = localStorage.getItem('user')
        if(user === null){
            store.dispatch('getInfo')
        }
        next()
    }
    else{
        if(whiteList.includes(to.path)){
            next()
        }else{
            next(`/login?redirect=${to.fullPath}`)
        }
    }
})



app.use(router).use(ElementPlus).use(store).mount('#app')
