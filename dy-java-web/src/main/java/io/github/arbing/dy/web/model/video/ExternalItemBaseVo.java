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
public class ExternalItemBaseVo {
    /**
     * 最近30天点赞数
     */
    @ApiModelProperty(value = "最近30天点赞数")
    @JSONField(alternateNames = "total_like", ordinal = 1)
    private Long totalLike;

    /**
     * 最近30天分享数
     */
    @ApiModelProperty(value = "最近30天分享数")
    @JSONField(alternateNames = "total_share", ordinal = 2)
    private Long totalShare;

    /**
     * 最近30天平均播放时长
     */
    @ApiModelProperty(value = "最近30天平均播放时长")
    @JSONField(alternateNames = "avg_play_duration", ordinal = 3)
    private Long avgPlayDuration;

    /**
     * 最近30天评论数
     */
    @ApiModelProperty(value = "最近30天评论数")
    @JSONField(alternateNames = "total_comment", ordinal = 4)
    private Long totalComment;

    /**
     * 最近30天播放次数
     */
    @ApiModelProperty(value = "最近30天播放次数")
    @JSONField(alternateNames = "total_play", ordinal = 5)
    private Long totalPlay;
}
