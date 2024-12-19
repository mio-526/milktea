import request from "@/util/request.js"


export function list(params){
    return request({
        url: '/api/category/list',
        method: 'GET',
        params
    })
}

export function get(id){
    return request({
        url: '/api/category/' + id,
        method: 'GET',
    })
}

export function add(data){
    return request({
        url: '/api/category',
        method: 'POST',
        data
    })
}

export function edit(data){
    return request({
        url: '/api/category',
        method: 'PUT',
        data
    })
}

export function del(ids){
    return request({
        url: '/api/category/' + ids,
        method: 'DELETE'
    })
}
