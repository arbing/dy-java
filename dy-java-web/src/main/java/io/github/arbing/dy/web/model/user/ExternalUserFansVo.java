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
public class ExternalUserFansVo {
    /**
     * 日期，yyyy-MM-dd
     */
    @ApiModelProperty(value = "日期，yyyy-MM-dd", example = "yyyy-MM-dd")
    @JSONField(alternateNames = "date", ordinal = 1)
    private String date;

    /**
     * 每日总粉丝数
     */
    @ApiModelProperty(value = "每日总粉丝数")
    @JSONField(alternateNames = "total_fans", ordinal = 2)
    private Long totalFans;

    /**
     * 每天新粉丝数
     */
    @ApiModelProperty(value = "每天新粉丝数")
    @JSONField(alternateNames = "new_fans", ordinal = 2)
    private Long newFans;

}
