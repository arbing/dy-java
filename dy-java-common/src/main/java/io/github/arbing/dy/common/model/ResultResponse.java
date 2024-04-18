package io.github.arbing.dy.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultResponse<T> extends DyBaseVo {
    /**
     * 结果数据
     */
    @ApiModelProperty(value = "结果数据")
    @JSONField(alternateNames = "result", ordinal = 1)
    private T result;
}
