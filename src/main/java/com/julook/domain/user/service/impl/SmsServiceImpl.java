package com.julook.domain.user.service.impl;

import com.julook.domain.user.dao.SmsCertificationDao;
import com.julook.domain.user.dto.request.SMSRequestDTO;
import com.julook.domain.user.service.SmsService;
import com.julook.global.exception.AuthNumberMismatchException;
import com.julook.global.exception.SmsSendFailedException;
import com.julook.global.utils.SmsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsUtils smsUtils;
    private final SmsCertificationDao smsCertificationDao;


    @Override
    public void sendSms(SMSRequestDTO userRequest) {
        String to = userRequest.getPhone();
        String certificationNumber = makeRandomNumber();

        System.out.println(certificationNumber);
        System.out.println(userRequest.getPhone());

        try {
            SingleMessageSentResponse result = smsUtils.sendOne(to, certificationNumber);
            System.out.println(result);
            if (!result.getStatusCode().equals("2000")) {
                throw new SmsSendFailedException();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        smsCertificationDao.createSmsCertification(to,certificationNumber);
    }

    @Override
    public void verifySms(SMSRequestDTO userRequest) {
        if (isVerify(userRequest)) {
            smsCertificationDao.removeSmsCertification(userRequest.getPhone());
        } else {
            throw new AuthNumberMismatchException("인증번호가 일치하지 않습니다.");
        }
    }

    private boolean isVerify(SMSRequestDTO userRequest) {
        return (smsCertificationDao.hasKey(userRequest.getPhone()) &&
                smsCertificationDao.getSmsCertification(userRequest.getPhone())
                        .equals(userRequest.getCertificationNumber()));
    }

    // 랜덤 인증 번호 생성
    private String makeRandomNumber() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

}
