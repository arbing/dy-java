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
public class DySimpleResult<T> implements IDySimpleBaseVo {
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    @JSONField(name = "data", ordinal = 1)
    private T data;

    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    @JSONField(name = "err_no", ordinal = 2)
    private Long errNo;

    /**
     * 错误码描述
     */
    @ApiModelProperty(value = "错误码描述")
    @JSONField(name = "err_msg", ordinal = 3)
    private String errMsg;

    /**
     * 错误描述
     */
    @ApiModelProperty(value = "错误描述")
    @JSONField(name = "err_tips", ordinal = 4)
    private String errTips;

    /**
     * 标识请求的唯一id
     */
    @ApiModelProperty(value = "标识请求的唯一id")
    @JSONField(name = "log_id", ordinal = 5)
    private String logId;
}
