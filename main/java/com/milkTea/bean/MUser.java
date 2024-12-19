package com.milkTea.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("m_user")
@AllArgsConstructor
@NoArgsConstructor
public class MUser {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_name",condition = SqlCondition.LIKE)
    private String userName;
    private String password;
    private Integer role;
    @TableField(value = "email",condition = SqlCondition.LIKE)
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
