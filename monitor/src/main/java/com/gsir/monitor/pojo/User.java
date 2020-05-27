package com.gsir.monitor.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@TableName("account_users")
public class User implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8601281110905609477L;

    @TableId(type=IdType.AUTO)
    private Integer id;

    @TableField(value="username")
    private String userName;

    private String password;
}
