import request from "@/util/request.js";

export function login(data){
    return request({
        url: '/api/login',
        method: 'POST',
        data
    })
}

export function register(data){
    return request({
        url: '/api/register',
        method: 'POST',
        data
    })
}

export function layout(data){
    return request({
        url: '/api/layout',
        method: 'POST',
        data
    })
}
