package io.github.arbing.dy.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DyBaseVo implements IDyBaseVo {
    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    @JSONField(alternateNames = "error_code")
    private Long errorCode;

    /**
     * 错误码描述
     */
    @ApiModelProperty(value = "错误码描述")
    @JSONField(alternateNames = "description")
    private String description;
}
