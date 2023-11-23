package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.SMSRequestDTO;

public interface SmsService {
    void sendSms(SMSRequestDTO userRequest);
    void verifySms(SMSRequestDTO userRequest);
}
