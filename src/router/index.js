import { createRouter, createWebHistory } from 'vue-router'

const routes = [

    {
        name: 'login',
        path: '/login',
        component: () => import('@/views/Login.vue'),
    },
    {
        name: 'register',
        path: '/register',
        component: () => import('@/views/Register.vue'),
    },
    {
        path: '/',
        redirect: '/user/index'
    },
    {
        path: '/user',
        name: 'User',
        component: () => import('@/views/user/layout/Index.vue'),
        children: [
            // 首页
            {
                name: 'UserIndex',
                path: '/user/index',
                component: () => import('@/views/user/Index.vue')
            },
            // 个人中心
            {
                name: 'Person',
                path: '/person',
                component: () => import('@/views/user/Person.vue')
            },
            // 支付
            {
                name: 'Pay',
                path: '/pay',
                component: () => import('@/views/user/Pay.vue')
            },
            // 订单
            {
                name: 'Order',
                path: '/order',
                component: () => import('@/views/user/Order.vue')
            },
            {
                name: 'OrderDetail',
                path: '/orderDetail',
                component: () => import('@/views/user/OrderDetail.vue')
            }
        ]
    },
    {
        name: 'Admin',
        path: '/admin',
        component: () => import('@/views/admin/Layout/Index.vue'),
        redirect: '/admin/index',
        children: [
            {
                name: 'AdminIndex',
                path: '/admin/index',
                component: () => import('@/views/admin/Index.vue')
            },
            // 用户管理
            {
                name: 'AdminUser',
                path: '/admin/user',
                component: () => import('@/views/admin/user/Index.vue')
            },
            {
                name: 'AdminAddAndEditUser',
                path: '/admin/addAndEdit/user',
                component: () => import('@/views/admin/user/AddAndEdit.vue')
            },
            // 订单管理
            {
                name: 'AdminOrder',
                path: '/admin/order',
                component: () => import('@/views/admin/order/Index.vue')
            },
            // 订单详情
            {
                name: 'AdminOrderDetail',
                path: '/admin/orderDetail',
                component: () => import('@/views/admin/orderDetail/Index.vue')
            },
            // 商品管理
            {
                name: 'AdminProduct',
                path: '/admin/product',
                component: () => import('@/views/admin/product/Index.vue')
            },
            {
                name: 'AdminAddAndEditProduct',
                path: '/admin/addAndEdit/product',
                component: () => import('@/views/admin/product/AddAndEdit.vue')
            },
            {
                name: 'AdminAddInventory',
                path: '/admin/addInventory',
                component: () => import('@/views/admin/product/AddInventory.vue')
            },
            // 原料管理
            {
                name: 'AdminInventory',
                path: '/admin/inventory',
                component: () => import('@/views/admin/inventory/Index.vue')
            },
            {
                name: 'AdminAddAndEditInventory',
                path: '/admin/addAndEdit/inventory',
                component: () => import('@/views/admin/inventory/AddAndEdit.vue')
            },
            // 类别管理
            {
                name: 'AdminCategory',
                path: '/admin/category',
                component: () => import('@/views/admin/category/Index.vue')
            },
            {
                name: 'AdminAddAndEditCategory',
                path: '/admin/addAndEdit/category',
                component: () => import('@/views/admin/category/AddAndEdit.vue')
            },
            // 员工管理
            {
                name: 'AdminEmployee',
                path: '/admin/employee',
                component: () => import('@/views/admin/employee/Index.vue')
            },
            {
                name: 'AdminAddAndEditEmployee',
                path: '/admin/addAndEdit/employee',
                component: () => import('@/views/admin/employee/AddAndEdit.vue')
            },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
