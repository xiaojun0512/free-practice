package com.cd.wj.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公司性质
 * </p>
 *
 * @author xj
 * @since 2022-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CompanyType对象", description="公司性质")
public class CompanyType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "性质")
    private String nature;


}
