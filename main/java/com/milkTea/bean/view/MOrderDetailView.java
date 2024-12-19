package com.milkTea.bean.view;

import com.milkTea.bean.MOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MOrderDetailView extends MOrderDetail {

    private String productName;
    private String productImgUrl;
    private String productPrice;
}
