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
public class AccessTokenRequest {
    /**
     * 应用唯一标识
     */
    @ApiModelProperty(value = "应用唯一标识", required = true)
    @JSONField(name = "client_key", ordinal = 1)
    private String clientKey;

    /**
     * 应用唯一标识对应的密钥
     */
    @ApiModelProperty(value = "应用唯一标识对应的密钥", required = true)
    @JSONField(name = "client_secret", ordinal = 2)
    private String clientSecret;

    /**
     * 固定值"authorization_code"
     */
    @ApiModelProperty(value = "固定值\"authorization_code\"", required = true)
    @JSONField(name = "grant_type", ordinal = 3)
    private String grantType = "authorization_code";

    /**
     * 授权码
     */
    @ApiModelProperty(value = "授权码", required = true)
    @JSONField(name = "code", ordinal = 4)
    private String code;
}
