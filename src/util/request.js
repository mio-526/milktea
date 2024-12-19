import axios from "axios";
import { ElMessage } from "element-plus";
import router from "../router/index.js";


axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'



const service = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 10000
})

service.interceptors.request.use(config => {
    config.headers['token'] = localStorage.getItem('token')
    return config
},error=>{
    Promise.reject(error)
})

service.interceptors.response.use(res=>{
    const code = res.data.code || 200
    const msg = res.data.msg || '系统未知错误，请反馈给管理员'
    if(code == 500){
        ElMessage({
            message: msg,
            type: 'error'
        })
    }else if(code == 401 || code == 405){
        ElMessage({
            message: msg,
            type: 'warning'
        })
        localStorage.removeItem('token')
        router.push('/user/login')
    }else if(code == 403){
        ElMessage({
            message: msg,
            type: 'warning'
        })
    }
    return res.data
},error=>{
    let {message} = error
    if(message.includes('Request failed with status code 404')){
        message = "接口访问404异常"
    }else if(message.includes('Network Error')){
        message = "后端接口连接超时"
    }else if(message.includes('Request failed with status code 500')){
        message = "系统接口500异常"
    } else{
        message = "系统未知错误，请反馈给管理员"
    }
    ElMessage({
        type: 'error',
        message: message
    })
    return Promise.reject(error)
})

export default service
