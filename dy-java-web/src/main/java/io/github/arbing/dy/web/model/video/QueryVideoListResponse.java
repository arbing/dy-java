package io.github.arbing.dy.web.model.video;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.arbing.dy.common.model.DyBaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryVideoListResponse extends DyBaseVo {
    /**
     * 是否有更多的数据
     */
    @ApiModelProperty(value = "是否有更多的数据")
    @JSONField(alternateNames = "has_more", ordinal = 1)
    private Boolean hasMore = false;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    @JSONField(alternateNames = "list", ordinal = 2)
    private List<VideoInfoVo> list;

    /**
     * 用于下一页请求的cursor
     */
    @ApiModelProperty(value = "用于下一页请求的cursor")
    @JSONField(alternateNames = "cursor", ordinal = 3)
    private Long cursor;
}
