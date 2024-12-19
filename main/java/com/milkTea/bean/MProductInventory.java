package com.milkTea.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("m_product_inventory")
public class MProductInventory {
    private Integer productId;
    private Integer inventoryId;
    private Integer num;
}
