import request from "@/util/request.js"

export function addBatch(data){
    return request({
        url: '/api/productInventory/batch',
        method: 'POST',
        data
    })
}
