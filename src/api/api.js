import request from "@/util/request.js";

export function getInfo(){
    return request({
        url: '/api/getInfo',
        method: 'GET'
    })
}

export  function createOrder(data){
    return request({
        url: '/api/createOrder',
        method: 'POST',
        data
    })
}

export  function listOrder(){
    return request({
        url: '/api/orderList',
        method: 'GET'
    })
}

export  function orderByOrderNo(orderNo){
    return request({
        url: '/api/orderByOrderNo/' + orderNo,
        method: 'GET'
    })
}

export  function cancelOrder(data){
    return request({
        url: '/api/cancelOrder',
        method: 'PUT',
        data
    })
}

export  function daySalesNum(){
    return request({
        url: '/api/daySalesNum',
        method: 'GET'
    })
}

export  function fiveDaysOrderNum(){
    return request({
        url: '/api/fiveDaysOrderNum',
        method: 'GEt'
    })
}

export  function inventoryConsumptionNum(data){
    return request({
        url: '/api/inventoryConsumptionNum',
        method: 'GET'
    })
}

export  function restPassword(data){
    return request({
        url: '/api/restPassword',
        method: 'PUT',
        data
    })
}


