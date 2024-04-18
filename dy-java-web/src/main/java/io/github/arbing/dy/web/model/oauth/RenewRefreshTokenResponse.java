package io.github.arbing.dy.web.model.oauth;

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
public class RenewRefreshTokenResponse {
    /**
     * 用户刷新 access_token
     */
    @ApiModelProperty(value = "用户刷新 access_token")
    @JSONField(alternateNames = "refresh_token", ordinal = 1)
    private String refreshToken;

    /**
     * 过期时间，单位（秒）
     */
    @ApiModelProperty(value = "过期时间，单位（秒）")
    @JSONField(alternateNames = "expires_in", ordinal = 2)
    private Integer expiresIn;
}
