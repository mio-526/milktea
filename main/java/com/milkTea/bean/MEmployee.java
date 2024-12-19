package com.milkTea.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("m_employee")
public class MEmployee {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String position;
    private String phone;
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireTime;
    private Integer status;
}
