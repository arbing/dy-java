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
public class DyResult<T> {
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    @JSONField(name = "data", ordinal = 1)
    private T data;

    /**
     * 扩展信息
     */
    @ApiModelProperty(value = "扩展信息")
    @JSONField(name = "extra", ordinal = 2)
    private DyExtra extra;

    /**
     * 错误描述
     */
    @ApiModelProperty(value = "错误描述")
    @JSONField(name = "message", ordinal = 3)
    private String message;
}
