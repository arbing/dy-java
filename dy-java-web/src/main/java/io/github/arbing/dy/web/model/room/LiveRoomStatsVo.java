package io.github.arbing.dy.web.model.room;

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
public class LiveRoomStatsVo {
    /**
     * 业务线id：1-抖火 3-西瓜头条
     */
    @ApiModelProperty(value = "业务线id：1-抖火 3-西瓜头条")
    @JSONField(alternateNames = "live_id", ordinal = 1)
    private Integer liveId;

    /**
     * 房间id
     */
    @ApiModelProperty(value = "房间id")
    @JSONField(alternateNames = "item", ordinal = 2)
    private String item;

    /**
     * 指标类型：3-房间，2-主播，1-用户
     */
    @ApiModelProperty(value = "指标类型：3-房间，2-主播，1-用户")
    @JSONField(alternateNames = "item_type", ordinal = 3)
    private Integer itemType;

    /**
     * 数据，浮点型
     */
    @ApiModelProperty(value = "数据，浮点型")
    @JSONField(alternateNames = "value_float", ordinal = 4)
    private Double valueFloat;

    /**
     * 时间槽
     */
    @ApiModelProperty(value = "时间槽")
    @JSONField(alternateNames = "timeslot", ordinal = 5)
    private String timeslot;

    /**
     * 指标名
     * <p>
     * 指标枚举：
     * "live_comment_ucnt_td"-直播间发送评论人数
     * "live_comment_cnt_td"-直播间评论次数
     * "live_share_cnt_td"- 直播间被转发次数
     * "live_like_cnt_td"- 直播间有点赞行为的次数
     * <p>
     * "live_watch_cnt_td"-直播间当日观看次数
     * "live_watch_ucnt_td"-直播间当日观看人数
     * "live_watch_duration_td"-直播间当日观看总时长
     * "per_minute_watch_ucnt_td_max"-直播间最高同时在线人数
     * "live_fans_watch_ucnt_td"-粉丝观看人数
     * "live_fans_watch_duration_td"-粉丝观看总时长
     * <p>
     * "live_start_ts"-直播开始时间
     * "live_end_ts"-直播结束时间
     * "live_duration_td"-直播时长
     * "live_follow_ucnt_td"-直播间内点击关注的人数
     */
    @ApiModelProperty(value = "指标名")
    @JSONField(alternateNames = "name", ordinal = 6)
    private String name;

    /**
     * 数据，整型
     */
    @ApiModelProperty(value = "数据，整型")
    @JSONField(alternateNames = "value", ordinal = 7)
    private Long value;
}
