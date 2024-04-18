package io.github.arbing.dy.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DyExtra implements IDyBaseVo {
    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    @JSONField(alternateNames = "error_code", ordinal = 1)
    private Long errorCode;

    /**
     * 错误码描述
     */
    @ApiModelProperty(value = "错误码描述")
    @JSONField(alternateNames = "description", ordinal = 2)
    private String description;

    /**
     * 子错误码
     */
    @ApiModelProperty(value = "子错误码")
    @JSONField(alternateNames = "sub_error_code", ordinal = 3)
    private Long subErrorCode;

    /**
     * 子错误码描述
     */
    @ApiModelProperty(value = "子错误码描述")
    @JSONField(alternateNames = "sub_description", ordinal = 4)
    private String subDescription;

    /**
     * 毫秒级时间戳
     */
    @ApiModelProperty(value = "毫秒级时间戳")
    @JSONField(alternateNames = "now", ordinal = 5)
    private Long now;

    /**
     * 标识请求的唯一id
     */
    @ApiModelProperty(value = "标识请求的唯一id")
    @JSONField(alternateNames = "logid", ordinal = 6)
    private String logid;


}
