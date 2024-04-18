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
public class RenewRefreshTokenRequest {
    /**
     * 应用唯一标识
     */
    @ApiModelProperty(value = "应用唯一标识", required = true)
    @JSONField(name = "client_key", ordinal = 1)
    private String clientKey;

    /**
     * 填写通过 access_token 获取到的 refresh_token 参数
     */
    @ApiModelProperty(value = "填写通过 access_token 获取到的 refresh_token 参数", required = true)
    @JSONField(name = "refresh_token", ordinal = 4)
    private String refreshToken;
}
