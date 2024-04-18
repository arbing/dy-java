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
public class ExternalItemCommentVo {
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @JSONField(alternateNames = "date", ordinal = 1)
    private String date;

    /**
     * 每日评论数
     */
    @ApiModelProperty(value = "每日评论数")
    @JSONField(alternateNames = "comment", ordinal = 2)
    private Long comment;
}
