package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.SMSRequestDTO;

public interface SmsSignInService {
    // 인증 문자 보내기
    void sendSms(String phone);
    // 문자 인증하기
    void verifySms(SMSRequestDTO userRequest);

}
