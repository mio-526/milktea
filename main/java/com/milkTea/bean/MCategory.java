package com.milkTea.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("m_category")
public class MCategory {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name",condition = SqlCondition.LIKE)
    private String name;
}
