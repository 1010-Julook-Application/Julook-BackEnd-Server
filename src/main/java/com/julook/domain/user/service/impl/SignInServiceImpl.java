package com.julook.domain.user.service.impl;

import com.julook.domain.user.dto.response.SkipSignInResponseDTO;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.repository.SignInRepository;
import com.julook.domain.user.service.SignInService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SignInServiceImpl implements SignInService {
    private final SignInRepository skipAuthSignInRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public SignInServiceImpl(SignInRepository skipAuthSignInRepository, ModelMapper modelMapper) {
        this.skipAuthSignInRepository = skipAuthSignInRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean isUserIdDuplicate(Long userID) {
        User user = skipAuthSignInRepository.findByUserID(userID);
        return user != null;
    }

    @Override
    public Long generateUniqueRandomId() {
        Long userId;
        boolean isDuplicate;
        do {
            // 랜덤 아이디 생성
            userId = generateRandomId();

            // 중복 체크: 데이터베이스에서 아이디 조회
            isDuplicate = isUserIdDuplicate(userId);
        } while (isDuplicate);

        return userId;
    }

    @Override
    public SkipSignInResponseDTO registerUserResults(Long userId, String nickName, String gender, String ageGroup) {
        SkipSignInResponseDTO responseDTO = new SkipSignInResponseDTO();
        try {
            Boolean isUserSaved = skipAuthSignInRepository.setUserInfo(userId,nickName,gender,ageGroup);

            if (isUserSaved) {
                User user = skipAuthSignInRepository.findByUserID(userId);

                if (user == null){
                    return null;
                }
                responseDTO = modelMapper.map(user, SkipSignInResponseDTO.class);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return  responseDTO;
    }

    private Long generateRandomId() {
        Random random = new Random();

        long min = 1000000000L;
        long max = 2000000000L;

        // 범위 내의 랜덤 아이디 생성
        long randomId = min + ((long) (random.nextDouble() * (max - min)));

        return randomId;
    }
}
