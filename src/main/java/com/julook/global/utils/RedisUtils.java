package com.julook.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class RedisUtils {
    private final String PREFIX = "sms:";
    private final int LIMIT_TIME = 3 * 60;

    private final StringRedisTemplate redisTemplate;

    public void createSmsCertification(String phone, String certificationNumber) {
        redisTemplate.opsForValue()
                .set(PREFIX + phone, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
    }

    public String getSmsCertification(String phone) {
        return redisTemplate.opsForValue().get(PREFIX + phone);
    }

    public void removeSmsCertification(String phone) {
        redisTemplate.delete(PREFIX + phone);
    }

    public boolean hasKey(String phone) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(PREFIX + phone));
    }



//    public void setData(String key, String value,Long expiredTime){
//        redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
//    }
//
//    public String getData(String key){
//        return (String) redisTemplate.opsForValue().get(key);
//    }
//
//    public void deleteData(String key){
//        redisTemplate.delete(key);
//    }
}
