import request from "@/util/request.js"


export function list(params){
    return request({
        url: '/api/product/list',
        method: 'GET',
        params
    })
}

export function get(id){
    return request({
        url: '/api/product/' + id,
        method: 'GET',
    })
}

export function getProductInventory(id){
    return request({
        url: '/api/product/productInventory/' + id,
        method: 'GET',
    })
}

export function add(data){
    return request({
        url: '/api/product',
        method: 'POST',
        data
    })
}

export function edit(data){
    return request({
        url: '/api/product',
        method: 'PUT',
        data
    })
}

export function del(ids){
    return request({
        url: '/api/product/' + ids,
        method: 'DELETE'
    })
}
