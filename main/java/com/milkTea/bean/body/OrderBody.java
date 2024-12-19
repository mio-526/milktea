package com.milkTea.bean.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBody {
    private Integer productId;
    private Integer num;
    private BigDecimal price;

}
