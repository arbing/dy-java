package io.github.arbing.dy.web.model.room;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LiveRoomResponse {
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    @JSONField(alternateNames = "stats", ordinal = 1)
    private List<LiveRoomStatsVo> stats = new ArrayList<>();

    /**
     * 数据准备状态
     */
    @ApiModelProperty(value = "数据准备状态")
    @JSONField(alternateNames = "data_ready_status", ordinal = 2)
    private List<LiveRoomDataReadyStatusVo> dataReadyStatus;
}
