package com.julook.domain.user.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.user.dto.request.SMSRequestDTO;
import com.julook.domain.user.dto.response.CheckAccountResponseDTO;
import com.julook.domain.user.service.SmsService;
import com.julook.domain.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/sms-certification")
public class SmsController {
    private final SmsService smsSignInService;
    private final UserAccountService userAccountService;

    @Autowired
    public SmsController(SmsService smsSignInService, UserAccountService userAccountService) {
        this.smsSignInService = smsSignInService;
        this.userAccountService = userAccountService;
    }

    // 인증번호 전달
    @PostMapping("/send")
    public ResponseEntity<ApiResponseDTO<?>> sendSms(@RequestBody SMSRequestDTO requestDTO) throws Exception {
        try{
            smsSignInService.sendSms(requestDTO);

            ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                    .status(HttpStatus.OK.value())
                    .resultMsg("SMS 전송 성공")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();

            ApiResponseDTO<Object> errorResponse = ApiResponseDTO.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .resultMsg("Error sending SMS: " + ex.getMessage())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // 인증번호 확인
    @PostMapping("/confirm")
    public ResponseEntity<ApiResponseDTO<?>> smsVerification(@RequestBody SMSRequestDTO requestDTO) throws Exception{
        try{
            smsSignInService.verifySms(requestDTO);

            ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                    .status(HttpStatus.OK.value())
                    .resultMsg("SMS 본인 인증 성공")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();

            ApiResponseDTO<Object> errorResponse = ApiResponseDTO.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .resultMsg("Error verifying SMS: " + ex.getMessage())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
