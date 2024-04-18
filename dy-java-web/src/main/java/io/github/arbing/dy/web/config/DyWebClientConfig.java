package io.github.arbing.dy.web.config;

import java.util.concurrent.locks.Lock;

public interface DyWebClientConfig {
    /**
     * 应用唯一标识
     *
     * @return
     */
    String getClientKey();

    /**
     * 应用唯一标识对应的密钥
     *
     * @return
     */
    String getClientSecret();

    /**
     * 授权成功后的回调地址
     *
     * @return
     */
    String getRedirectUri();

    /**
     * 接口基地址
     *
     * @return
     */
    default String getBaseUrl() {
        return "https://open.douyin.com";
    }

    /**
     * 获取应用凭证
     *
     * @return
     */
    String getClientToken();

    /**
     * 获取应用凭证锁
     *
     * @return
     */
    Lock getClientTokenLock();

    /**
     * 更新应用凭证
     *
     * @param clientToken
     * @param expiresIn
     */
    void updateClientToken(String clientToken, Integer expiresIn);

    /**
     * 过期应用凭证
     */
    void expireClientToken();
}
