package com.milkTea.bean.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonView {

    private Integer orderNum;

    private Integer totalNum;

    private String inventoryName;

}
