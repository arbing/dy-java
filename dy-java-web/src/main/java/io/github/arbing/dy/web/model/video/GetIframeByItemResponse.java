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
public class GetIframeByItemResponse {
    /**
     * 返回IFrame代码片段，用于直接注入页面
     */
    @ApiModelProperty(value = "返回IFrame代码片段，用于直接注入页面")
    @JSONField(alternateNames = "iframe_code", ordinal = 1)
    private String iframeCode;

    /**
     * 视频标题
     */
    @ApiModelProperty(value = "视频标题")
    @JSONField(alternateNames = "video_title", ordinal = 2)
    private String videoTitle;

    /**
     * 视频宽度
     */
    @ApiModelProperty(value = "视频宽度")
    @JSONField(alternateNames = "video_width", ordinal = 3)
    private Long videoWidth;

    /**
     * 视频高度
     */
    @ApiModelProperty(value = "视频高度")
    @JSONField(alternateNames = "video_height", ordinal = 4)
    private Long videoHeight;

}
