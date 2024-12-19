import request from "@/util/request.js"


export function list(params){
    return request({
        url: '/api/order/list',
        method: 'GET',
        params
    })
}

export function get(id){
    return request({
        url: '/api/order/' + id,
        method: 'GET',
    })
}

export function add(data){
    return request({
        url: '/api/order',
        method: 'POST',
        data
    })
}

export function edit(data){
    return request({
        url: '/api/order',
        method: 'PUT',
        data
    })
}

export function success(data){
    return request({
        url: '/api/order/success',
        method: 'PUT',
        data
    })
}

export function del(ids){
    return request({
        url: '/api/order/' + ids,
        method: 'DELETE'
    })
}
