package io.github.arbing.dy.web.config;

import java.util.concurrent.locks.Lock;

public interface DyWebUserConfig {
    /**
     * 应用唯一标识
     *
     * @return
     */
    String getClientKey();

    /**
     * 授权用户唯一标识
     *
     * @return
     */
    String getOpenId();

    /**
     * 接口基地址
     *
     * @return
     */
    default String getBaseUrl() {
        return "https://open.douyin.com";
    }

    /**
     * 获取接口调用凭证
     *
     * @return
     */
    String getAccessToken();

    /**
     * 获取接口调用凭证锁
     *
     * @return
     */
    Lock getAccessTokenLock();

    /**
     * 更新接口调用凭证
     *
     * @param accessToken
     * @param expiresIn
     */
    void updateAccessToken(String accessToken, Integer expiresIn);

    /**
     * 过期接口调用凭证
     */
    void expireAccessToken();

    /**
     * 获取刷新Token
     *
     * @return
     */
    String getRefreshToken();

    /**
     * 获取刷新Token锁
     *
     * @return
     */
    Lock getRefreshTokenLock();

    /**
     * 更新刷新Token
     *
     * @param refreshToken
     * @param refreshExpiresIn
     */
    void updateRefreshToken(String refreshToken, Integer refreshExpiresIn);

    /**
     * 过期刷新Token
     */
    void expireRefreshToken();
}
