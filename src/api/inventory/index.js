import request from "@/util/request.js"


export function list(params){
    return request({
        url: '/api/inventory/list',
        method: 'GET',
        params
    })
}

export function get(id){
    return request({
        url: '/api/inventory/' + id,
        method: 'GET',
    })
}

export function add(data){
    return request({
        url: '/api/inventory',
        method: 'POST',
        data
    })
}

export function edit(data){
    return request({
        url: '/api/inventory',
        method: 'PUT',
        data
    })
}

export function del(ids){
    return request({
        url: '/api/inventory/' + ids,
        method: 'DELETE'
    })
}
