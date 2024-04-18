package io.github.arbing.dy.web.model.video;

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
public class ExternalItemLikeVo {
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @JSONField(alternateNames = "date", ordinal = 1)
    private String date;

    /**
     * 每日点赞数
     */
    @ApiModelProperty(value = "每日点赞数")
    @JSONField(alternateNames = "like", ordinal = 2)
    private Long like;
}
