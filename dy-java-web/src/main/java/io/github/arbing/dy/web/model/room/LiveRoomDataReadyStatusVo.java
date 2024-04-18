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
public class LiveRoomDataReadyStatusVo {
    /**
     * 业务线id：1-抖火 3-西瓜头条
     */
    @ApiModelProperty(value = "业务线id：1-抖火 3-西瓜头条")
    @JSONField(alternateNames = "live_id", ordinal = 1)
    private Integer liveId;

    /**
     * 当前时间分区数据是否产出
     */
    @ApiModelProperty(value = "当前时间分区数据是否产出")
    @JSONField(alternateNames = "is_ready", ordinal = 2)
    private Boolean isReady = false;

    /**
     * 指标类型：3-房间，2-主播，1-用户
     */
    @ApiModelProperty(value = "指标类型：3-房间，2-主播，1-用户")
    @JSONField(alternateNames = "item_type", ordinal = 3)
    private Integer itemType;

    /**
     * 指标名
     * <p>
     * 指标枚举：
     * "live_comment_ucnt_td"-直播间发送评论人数
     * "live_comment_cnt_td"-直播间评论次数
     * "live_share_cnt_td"- 直播间被转发次数
     * "live_like_cnt_td"- 直播间有点赞行为的次数
     */
    @ApiModelProperty(value = "指标名")
    @JSONField(alternateNames = "name", ordinal = 4)
    private String name;

    /**
     * 时间槽
     */
    @ApiModelProperty(value = "时间槽")
    @JSONField(alternateNames = "time_slot", ordinal = 5)
    private String timeSlot;
}
