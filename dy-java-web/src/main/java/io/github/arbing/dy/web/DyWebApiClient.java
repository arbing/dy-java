package io.github.arbing.dy.web;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import io.github.arbing.dy.common.constant.DyConstants;
import io.github.arbing.dy.common.model.DyResult;
import io.github.arbing.dy.common.utils.http.DyHttpExecutor;
import io.github.arbing.dy.web.config.DyWebClientConfig;
import io.github.arbing.dy.web.model.oauth.AccessTokenRequest;
import io.github.arbing.dy.web.model.oauth.AccessTokenResponse;
import io.github.arbing.dy.web.model.oauth.ClientTokenRequest;
import io.github.arbing.dy.web.model.oauth.ClientTokenResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
@RequiredArgsConstructor
@Getter
public class DyWebApiClient {
    private final DyWebClientConfig clientConfig;
    private final DyHttpExecutor httpExecutor;

    /**
     * 抖音获取授权码
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/account-permission/douyin-get-permission-code
     *
     * @param scope
     * @param state
     * @return
     */
    public String buildConnectUrl(String scope, String state) {
        String url = String.format("https://open.douyin.com/platform/oauth/connect?client_key=%s&response_type=code&scope=%s&redirect_uri=%s&state=%s",
                this.getClientConfig().getClientKey(),
                scope,
                this.getClientConfig().getRedirectUri(),
                state
        );
        return url;
    }

    public Boolean checkSign(String signature, String msg) {
        String data = this.getClientConfig().getClientSecret() + msg;
        String sign = DigestUtils.sha1Hex(data);
        if (!sign.equals(signature)) {
            return false;
        }
        return true;
    }

    /**
     * 获取 access_token
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/account-permission/get-access-token
     */
    public AccessTokenResponse code2AccessToken(String code) {
        String path = "/oauth/access_token/";
        String url = this.getClientConfig().getBaseUrl().concat(path);
        val response = this.getHttpExecutor().executePost(url,
                null,
                AccessTokenRequest.builder()
                        .clientKey(this.getClientConfig().getClientKey())
                        .clientSecret(this.getClientConfig().getClientSecret())
                        .grantType("authorization_code")
                        .code(code)
                        .build(),
                ImmutableMap.of(DyConstants.CONTENT_TYPE, DyConstants.APPLICATION_X_WWW_FORM_URLENCODED),
                new TypeReference<DyResult<AccessTokenResponse>>() {
                });
        return response.getData();
    }

    /**
     * 生成 client_token
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/account-permission/client-token
     */
    public ClientTokenResponse getClientToken() {
        String path = "/oauth/client_token/";
        String url = this.getClientConfig().getBaseUrl().concat(path);
        val response = this.getHttpExecutor().executePost(url,
                null,
                ClientTokenRequest.builder()
                        .clientKey(this.getClientConfig().getClientKey())
                        .clientSecret(this.getClientConfig().getClientSecret())
                        .grantType("client_credential")
                        .build(),
                null,
                new TypeReference<DyResult<ClientTokenResponse>>() {
                });
        return response.getData();
    }
}
