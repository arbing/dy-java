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
public class VideoInfoVo {
    /**
     * 视频真实ID
     */
    @ApiModelProperty(value = "视频真实ID")
    @JSONField(alternateNames = "video_id", ordinal = 1)
    private String videoId;

    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id")
    @JSONField(alternateNames = "item_id", ordinal = 2)
    private String itemId;

    /**
     * 媒体类型。2:图集;4:视频
     */
    @ApiModelProperty(value = "媒体类型。2:图集;4:视频")
    @JSONField(alternateNames = "media_type", ordinal = 3)
    private Integer mediaType;

    /**
     * 视频标题
     */
    @ApiModelProperty(value = "视频标题")
    @JSONField(alternateNames = "title", ordinal = 3)
    private String title;

    /**
     * 视频封面
     */
    @ApiModelProperty(value = "视频封面")
    @JSONField(alternateNames = "cover", ordinal = 4)
    private String cover;

    /**
     * 是否置顶
     */
    @ApiModelProperty(value = "是否置顶")
    @JSONField(alternateNames = "is_top", ordinal = 5)
    private Boolean isTop = false;

    /**
     * 视频创建时间戳(秒)
     */
    @ApiModelProperty(value = "视频创建时间戳(秒)")
    @JSONField(alternateNames = "create_time", ordinal = 6)
    private Long createTime;

    /**
     * 表示是否审核结束。审核通过或者失败都会返回true，审核中返回false。
     */
    @ApiModelProperty(value = "表示是否审核结束。审核通过或者失败都会返回true，审核中返回false。")
    @JSONField(alternateNames = "is_reviewed", ordinal = 7)
    private Boolean isReviewed = false;

    /**
     * 视频状态。1:细化为5、6、7三种状态;2:不适宜公开;4:审核中;5:公开视频;6:好友可见;7:私密视频
     */
    @ApiModelProperty(value = "视频状态。1:细化为5、6、7三种状态;2:不适宜公开;4:审核中;5:公开视频;6:好友可见;7:私密视频")
    @JSONField(alternateNames = "video_status", ordinal = 8)
    private Integer videoStatus;

    /**
     * 视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页。
     */
    @ApiModelProperty(value = "视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页。")
    @JSONField(alternateNames = "share_url", ordinal = 9)
    private String shareUrl;

    /**
     * 统计数据
     */
    @ApiModelProperty(value = "统计数据")
    @JSONField(alternateNames = "statistics", ordinal = 10)
    private VideoStatVo statistics;


}
