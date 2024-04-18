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
public class ExternalUserShareVo {
    /**
     * 日期，yyyy-MM-dd
     */
    @ApiModelProperty(value = "日期，yyyy-MM-dd", example = "yyyy-MM-dd")
    @JSONField(alternateNames = "date", ordinal = 1)
    private String date;

    /**
     * 新增分享
     */
    @ApiModelProperty(value = "新增分享")
    @JSONField(alternateNames = "new_share", ordinal = 2)
    private Long newShare;

}
