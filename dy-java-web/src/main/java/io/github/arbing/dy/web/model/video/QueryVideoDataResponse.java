package io.github.arbing.dy.web.model.video;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.arbing.dy.common.model.DyBaseVo;
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
public class QueryVideoDataResponse extends DyBaseVo {
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    @JSONField(alternateNames = "list", ordinal = 1)
    private List<VideoInfoVo> list = new ArrayList<>();
}
