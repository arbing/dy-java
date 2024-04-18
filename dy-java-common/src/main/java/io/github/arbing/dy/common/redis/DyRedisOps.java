package io.github.arbing.dy.common.redis;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Redis相关操作
 * <p>
 * 该接口不承诺稳定, 外部实现请继承
 */
public interface DyRedisOps {

    String getValue(String key);

    void setValue(String key, String value, int expire, TimeUnit timeUnit);

    Long getExpire(String key);

    void expire(String key, int expire, TimeUnit timeUnit);

    Lock getLock(String key);
}

