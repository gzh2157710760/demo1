package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzh
 * @since 2020-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户登录名
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 用户姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("user_pass")
    private String userPass;

    /**
     * 用户签名
     */
    private String sign;

    /**
     * 用户状态
     */
    private Boolean status;

    /**
     * 用户电话
     */
    private String tel;

    /**
     * 用户qq号
     */
    private String qq;

    /**
     * 用户头像
     */
    private String icon;


}
