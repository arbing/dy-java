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
public class ExternalItemPlayVo {
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @JSONField(alternateNames = "date", ordinal = 1)
    private String date;

    /**
     * 每日播放数
     */
    @ApiModelProperty(value = "每日播放数")
    @JSONField(alternateNames = "play", ordinal = 2)
    private Long play;
}
