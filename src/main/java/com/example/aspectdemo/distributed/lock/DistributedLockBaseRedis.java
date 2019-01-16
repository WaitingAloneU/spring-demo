package com.example.aspectdemo.distributed.lock;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.UUID;

/**
 * 基于Redis的setNx命令的分布式锁简单实现
 */
public class DistributedLockBaseRedis {

    /**
     * redis
     */
    private RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

    /**
     *
     * @param keyName
     * @param timeout 毫秒值
     * @return
     */
    public String lockWithTimeout(String keyName, Long timeout){

        // 初始化redis的锁key名
        String lockKey = "lock:" + keyName;
        Boolean nxResult =  (Boolean) redisTemplate.execute((RedisCallback) connection -> {

            long expireAt = System.currentTimeMillis() + timeout + 1;
            Boolean acquire = connection.setNX(lockKey.getBytes(), String.valueOf(expireAt).getBytes());

            // 设置成功
            if (acquire) {
                return true;
            } else {
                // key已经存在
                byte[] value = connection.get(lockKey.getBytes());
                // value 不是null
                if (Objects.nonNull(value) && value.length > 0) {
                    // 转城市间
                    long expireTime = Long.parseLong(new String(value));
                    // 锁是否已经过期
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lockKey.getBytes(), String.valueOf(System.currentTimeMillis() + timeout + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    /**
     * 释放锁
     * @param keyName
     * @param uuid
     * @return
     */
    public String releaseLock(String keyName, String uuid){
        return null;
    }

}
