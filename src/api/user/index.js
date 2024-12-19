import request from "@/util/request.js"


export function list(params){
    return request({
        url: '/api/user/list',
        method: 'GET',
        params
    })
}

export function get(id){
    return request({
        url: '/api/user/' + id,
        method: 'GET',
    })
}

export function add(data){
    return request({
        url: '/api/user',
        method: 'POST',
        data
    })
}

export function edit(data){
    return request({
        url: '/api/user',
        method: 'PUT',
        data
    })
}

export function del(ids){
    return request({
        url: '/api/user/' + ids,
        method: 'DELETE'
    })
}
