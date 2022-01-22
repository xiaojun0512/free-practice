package com.cd.wj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("company")
@ApiModel(value = "公司信息")
public class Company {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "联系电话")
    private String tel;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "当前页码")
    @TableField(exist = false)
    private Integer current;
    @ApiModelProperty(value = "大小")
    @TableField(exist = false)
    private Integer page;
    @ApiModelProperty(value = "性质")
    @TableField(exist = false)
    private String nature;
}
