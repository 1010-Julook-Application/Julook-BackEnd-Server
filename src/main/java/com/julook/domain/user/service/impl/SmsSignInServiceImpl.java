package com.julook.domain.user.service.impl;

import com.julook.domain.user.dto.request.SMSRequestDTO;
import com.julook.domain.user.service.SmsSignInService;
import com.julook.global.exception.AuthNumberMismatchException;
import com.julook.global.exception.SmsSendFailedException;
import com.julook.global.utils.RedisUtils;
import com.julook.global.utils.SmsUtils;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

import java.util.Random;

public class SmsSignInServiceImpl implements SmsSignInService {
    private final RedisUtils redisUtils;

    public SmsSignInServiceImpl(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    // 인증번호 발송
    @Override
    public void sendSms(String phone) {
        String randomNumber = makeRandomNumber();
        SmsUtils smsUtils = new SmsUtils();

        System.out.println(randomNumber);
        try {
            SingleMessageSentResponse result = smsUtils.sendOne(phone, randomNumber);
            System.out.println(result);
            if (!result.getStatusCode().equals("200")) {
                throw new SmsSendFailedException();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        redisUtils.createSmsCertification(phone,randomNumber);
    }

    // 인증번호 동일한지 확인
    @Override
    public void verifySms(SMSRequestDTO userRequest) {
        if (isVerify(userRequest)) {
            throw new AuthNumberMismatchException("인증번호가 일치하지 않습니다.");
        }
        redisUtils.removeSmsCertification(userRequest.getPhone());
    }


    // 랜덤 인증 번호 생성
    private String makeRandomNumber() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    // 인증번호 확인
    private boolean isVerify(SMSRequestDTO requestDto) {
        return !(redisUtils.hasKey(requestDto.getPhone()) &&
                redisUtils.getSmsCertification(requestDto.getPhone())
                        .equals(requestDto.getCertificationNumber()));
    }
}
