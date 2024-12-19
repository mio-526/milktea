import request from "@/util/request.js"


export function list(params){
    return request({
        url: '/api/employee/list',
        method: 'GET',
        params
    })
}

export function get(id){
    return request({
        url: '/api/employee/' + id,
        method: 'GET',
    })
}

export function add(data){
    return request({
        url: '/api/employee',
        method: 'POST',
        data
    })
}

export function edit(data){
    return request({
        url: '/api/employee',
        method: 'PUT',
        data
    })
}

export function del(ids){
    return request({
        url: '/api/employee/' + ids,
        method: 'DELETE'
    })
}
