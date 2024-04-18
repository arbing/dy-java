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
public class VideoStatVo {
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    @JSONField(alternateNames = "digg_count", ordinal = 1)
    private Long diggCount;

    /**
     * 下载数
     */
    @ApiModelProperty(value = "下载数")
    @JSONField(alternateNames = "download_count", ordinal = 2)
    private Long downloadCount;

    /**
     * 播放数，只有作者本人可见。公开视频设为私密后，播放数也会返回0。
     */
    @ApiModelProperty(value = "播放数，只有作者本人可见。公开视频设为私密后，播放数也会返回0。")
    @JSONField(alternateNames = "play_count", ordinal = 3)
    private Long playCount;

    /**
     * 分享数
     */
    @ApiModelProperty(value = "分享数")
    @JSONField(alternateNames = "share_count", ordinal = 4)
    private Long shareCount;

    /**
     * 转发数
     */
    @ApiModelProperty(value = "转发数")
    @JSONField(alternateNames = "forward_count", ordinal = 5)
    private Long forwardCount;

    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数")
    @JSONField(alternateNames = "comment_count", ordinal = 6)
    private Long commentCount;
}
