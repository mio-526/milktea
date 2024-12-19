import { createStore } from 'vuex'
import { getInfo } from '@/api/api.js'


const store = createStore({
    state: {
        user: []
    },
    mutations: {
        SET_USER(state,user){
            localStorage.setItem('user',JSON.stringify(user))
        }
    },
    actions:{
        getInfo({commit}){
            getInfo().then(res=>{
                commit('SET_USER',res.data)
            })
        }
    }
})

export default store
