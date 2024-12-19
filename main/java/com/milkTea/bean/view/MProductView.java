package com.milkTea.bean.view;

import com.milkTea.bean.MProduct;
import com.milkTea.bean.MProductInventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MProductView extends MProduct {
    private String categoryName;

    private List<MProductInventory> productInventoryList;
}
