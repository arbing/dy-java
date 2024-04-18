package io.github.arbing.dy.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultListResponse<T> extends DyBaseVo {
    /**
     * 结果列表数据
     */
    @ApiModelProperty(value = "结果列表数据")
    @JSONField(alternateNames = "result_list", ordinal = 1)
    private List<T> resultList = new ArrayList<>();
}
