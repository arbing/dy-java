package io.github.arbing.dy.web.model.oauth;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.arbing.dy.common.model.DyBaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientTokenResponse extends DyBaseVo {
    /**
     * client_token 接口调用凭证
     */
    @ApiModelProperty(value = "client_token 接口调用凭证")
    @JSONField(alternateNames = "access_token", ordinal = 1)
    private String accessToken;

    /**
     * client_token 接口调用凭证超时时间，单位（秒）
     */
    @ApiModelProperty(value = "client_token 接口调用凭证超时时间，单位（秒）")
    @JSONField(alternateNames = "expires_in", ordinal = 2)
    private Integer expiresIn;
}
