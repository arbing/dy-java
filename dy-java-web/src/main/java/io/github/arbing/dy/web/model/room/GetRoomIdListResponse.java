package io.github.arbing.dy.web.model.room;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.arbing.dy.common.model.IDySimpleBaseVo;
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
public class GetRoomIdListResponse implements IDySimpleBaseVo {
    /**
     * 房间id列表
     */
    @ApiModelProperty(value = "房间id列表")
    @JSONField(alternateNames = "room_ids", ordinal = 1)
    private List<Long> roomIds = new ArrayList<>();

    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    @JSONField(name = "err_no", ordinal = 2)
    private Long errNo;

    /**
     * 错误码描述
     */
    @ApiModelProperty(value = "错误码描述")
    @JSONField(name = "err_msg", ordinal = 3)
    private String errMsg;

    /**
     * 错误描述
     */
    @ApiModelProperty(value = "错误描述")
    @JSONField(name = "err_tips", ordinal = 4)
    private String errTips;

    /**
     * 标识请求的唯一id
     */
    @ApiModelProperty(value = "标识请求的唯一id")
    @JSONField(name = "log_id", ordinal = 5)
    private String logId;
}
