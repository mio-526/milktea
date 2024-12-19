import request from "@/util/request.js"


export function list(params){
    return request({
        url: '/api/orderDetail/list',
        method: 'GET',
        params
    })
}

export function get(id){
    return request({
        url: '/api/orderDetail/' + id,
        method: 'GET',
    })
}

export function add(data){
    return request({
        url: '/api/orderDetail',
        method: 'POST',
        data
    })
}

export function edit(data){
    return request({
        url: '/api/orderDetail',
        method: 'PUT',
        data
    })
}

export function del(ids){
    return request({
        url: '/api/orderDetail/' + ids,
        method: 'DELETE'
    })
}
