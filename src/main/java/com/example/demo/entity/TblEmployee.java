package com.example.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author gzh
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "tblEmployee", description = "人员实体")
public class TblEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工id
     */
    @ApiModelProperty(name="id",value = "人员id",required = false)
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    /**
     * 员工姓名
     */
    @ApiModelProperty(name="userName",value = "姓名",required = true)
    private String userName;

    /**
     * 邮箱
     */
    @ApiModelProperty(name="email",value = "邮箱",required = true)
    private String email;

    /**
     * 性别   1：男， 0：女
     */
    @ApiModelProperty(name="gender",value = "性别   1：男， 0：女",required = true)
    private String gender;

    /**
     * 年龄
     */
    @ApiModelProperty(name="age",value = "年龄",required = true)
    private Integer age;
}
