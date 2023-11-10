package com.julook.domain.user.controller;

import com.julook.domain.user.dto.request.SMSRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class SmsSignInController {

    // 인증번호 전송 Api
    @PostMapping("/smsSignIn/sends")
    public ResponseEntity<Void> sendSMS(@RequestBody SMSRequestDTO userRequest) {
        return null;
    }

    // 입력한 인증번호 검증 Api


}
