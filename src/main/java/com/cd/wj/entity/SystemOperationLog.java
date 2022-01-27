package com.cd.wj.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统操作日志
 * </p>
 *
 * @author xj
 * @since 2022-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SystemOperationLog对象", description="系统操作日志")
public class SystemOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "请求路径")
    private String url;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "发生时间")
    private LocalDateTime createTime;


}
