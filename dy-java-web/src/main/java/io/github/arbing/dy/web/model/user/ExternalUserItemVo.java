package io.github.arbing.dy.web.model.user;

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
public class ExternalUserItemVo {
    /**
     * 日期，yyyy-MM-dd
     */
    @ApiModelProperty(value = "日期，yyyy-MM-dd", example = "yyyy-MM-dd")
    @JSONField(alternateNames = "date", ordinal = 1)
    private String date;

    /**
     * 每日内容总数
     */
    @ApiModelProperty(value = "每日内容总数")
    @JSONField(alternateNames = "total_issue", ordinal = 2)
    private Long totalIssue;

    /**
     * 每日发布内容数
     */
    @ApiModelProperty(value = "每日发布内容数")
    @JSONField(alternateNames = "new_issue", ordinal = 3)
    private Long newIssue;

    /**
     * 每日新增视频播放
     */
    @ApiModelProperty(value = "每日新增视频播放")
    @JSONField(alternateNames = "new_play", ordinal = 4)
    private Long newPlay;
}
