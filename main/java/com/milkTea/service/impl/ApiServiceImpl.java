package com.milkTea.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milkTea.bean.*;
import com.milkTea.bean.view.CommonView;
import com.milkTea.bean.view.MOrderDetailView;
import com.milkTea.bean.view.MOrderView;
import com.milkTea.bean.view.MProductView;
import com.milkTea.service.*;
import com.milkTea.util.AjaxResult;
import com.milkTea.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@AllArgsConstructor
public class ApiServiceImpl implements ApiService {

    private final IMUserService userService;

    private final IMOrderService orderService;

    private final IMOrderDetailService orderDetailService;

    private final IMProductService productService;

    private final IMInventoryService inventoryService;

    private final IMProductInventoryService productInventoryService;

    @Override
    public AjaxResult getInfo() {
        return AjaxResult.success(CommonUtil.getUser());
    }

    @Override
    public AjaxResult createOrder(List<MOrderDetail> orderDetailList){
        MUser user = CommonUtil.getUser();
        String orderNo = CommonUtil.getOrderNo();
        BigDecimal total = new BigDecimal(0);
        for(MOrderDetail orderDetail : orderDetailList){
            // 原料减少
            MProductView mProductView = productService.selectProductInventoryByProductId(orderDetail.getProductId());
            if(ObjectUtil.isEmpty(mProductView)){
                return AjaxResult.error(String.format("当前%s商品不存在",mProductView.getName()));
            }
            if(!ObjectUtil.isEmpty(mProductView.getProductInventoryList()) && !mProductView.getProductInventoryList().isEmpty()){
                for(MProductInventory productInventory : mProductView.getProductInventoryList()){
                    MInventory mInventory = inventoryService.getById(productInventory.getInventoryId());
                    if(ObjectUtil.isEmpty(mInventory)){
                        return AjaxResult.error(String.format("当前%s原料不存在",mInventory.getName()));
                    }
                    int num = mInventory.getNum() - productInventory.getNum();
                    if(num < 0){
                        return AjaxResult.error(String.format("当前%s原料不足",mInventory.getName()));
                    }
                    mInventory.setNum(num);
                    inventoryService.updateById(mInventory);
                }
            }
            orderDetail.setOrderNo(orderNo);
            orderDetail.setCreateTime(new Date());
            orderDetail.setPrice(mProductView.getPrice());
            total = total.add(orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getNum())));
        }
        MOrder order = new MOrder();
        order.setOrderNo(orderNo);
        order.setUserId(user.getId());
        order.setCreateTime(new Date());
        order.setStatus(1);
        order.setTotalPrice(total);
        orderDetailService.saveBatch(orderDetailList);
        return AjaxResult.success( orderService.save(order));
    }

    @Override
    public AjaxResult cancelOrder(MOrder order) {
        MOrder mOrder = orderService.getOne(new LambdaQueryWrapper<>(order));
        if(mOrder.getStatus() == 3){
            return AjaxResult.error("当前订单已取消！");
        }
        if(mOrder.getStatus() != 2){
            // 还原原料
            List<MOrderDetail> mOrderDetailList = orderDetailService.list(new LambdaQueryWrapper<>(
                    new MOrderDetailView() {{
                        setOrderNo(order.getOrderNo());
                    }}
            ));
            if(!ObjectUtil.isEmpty(mOrderDetailList)){
                for(MOrderDetail orderDetail : mOrderDetailList){
                    List<MProductInventory> mProductInventorieList = productInventoryService.list(new LambdaQueryWrapper<>(
                            new MProductInventory() {{
                                setProductId(orderDetail.getProductId());
                            }}
                    ));
                    if(!ObjectUtil.isEmpty(mProductInventorieList) && !mProductInventorieList.isEmpty()){
                        for(MProductInventory productInventory : mProductInventorieList){
                            inventoryService.update().setSql("num = num + " + productInventory.getNum()).eq("id", productInventory.getInventoryId()).update();
                        }
                    }
                }
            }
        }
        mOrder.setStatus(3);
        return AjaxResult.success(orderService.updateById(mOrder));
    }

    @Override
    public AjaxResult orderList(MOrder order) {
        return AjaxResult.success(orderService.selectOrderListView(order));
    }

    @Override
    public AjaxResult getOrderByOrderNo(String orderNo) {
        return AjaxResult.success(orderService.selectOrderViewByOrderNo(orderNo));
    }

    @Override
    public AjaxResult getProductNum() {
        return AjaxResult.success(productService.count());
    }


    public AjaxResult getFiveDaysOrderNum(){
        Map<String,Integer> map = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 5; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date date = calendar.getTime();
            CommonView common = orderService.selectOrderNum(new MOrderView() {{
                setCreateTime(date);
            }});
            map.put(simpleDateFormat.format(date),common.getOrderNum());
        }
        return AjaxResult.success(map);
    }

    /**
     * 获取9，12，15，18，21各种原料消耗量
     * @return
     */
    public AjaxResult getInventoryConsumptionNum(){
        Calendar calendar = Calendar.getInstance();
        Map<String,List<Integer>> map = new HashMap<>();
        List<MInventory> list = inventoryService.list();
        int[] time = {9,12,15,18,21};
        for (int i = 0; i < time.length; i++) {
            if(time[i] == 9){
                list.forEach(item-> {
                    List<Integer> nums = new ArrayList<>();
                    nums.add(0);
                    map.put(item.getName(),nums);
                });
                continue;
            }
            calendar.set(Calendar.HOUR_OF_DAY, time[i]);
            Date date = calendar.getTime();
            List<CommonView> commonList = orderService.selectInventoryNum(new MOrderView() {{ setCreateTime(date); }});
            if(ObjectUtil.isEmpty(commonList)){
                list.forEach(item-> {
                    List<Integer> integers = map.get(item.getName());
                    integers.add(0);
                });
            }else{
                for(CommonView common : commonList){
                    List<Integer> integers = map.get(common.getInventoryName());
                    integers.add(common.getTotalNum());
                }
            }

        }
        return AjaxResult.success(map);
    }

    @Override
    public AjaxResult restPassword(MUser user) {
        user.setPassword(CommonUtil.encryptionPassword(user.getPassword()));
        return AjaxResult.success(userService.updateById(user));
    }

}
