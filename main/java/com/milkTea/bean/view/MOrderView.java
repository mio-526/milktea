package com.milkTea.bean.view;

import com.milkTea.bean.MOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MOrderView extends MOrder {
    private List<MOrderDetailView> orderDetailViewList;
    private String userName;
}
