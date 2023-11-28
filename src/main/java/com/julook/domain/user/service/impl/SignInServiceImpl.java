package com.julook.domain.user.service.impl;

import com.julook.domain.user.dto.request.PhoneSignInRequestDTO;
import com.julook.domain.user.dto.response.SignInResponseDTO;
import com.julook.domain.user.dto.response.UserActionResponseDTO;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.repository.ProfileRepository;
import com.julook.domain.user.repository.SignInRepository;
import com.julook.domain.user.service.SignInService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SignInServiceImpl implements SignInService {
    private final SignInRepository signInRepository;
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public SignInServiceImpl(SignInRepository skipAuthSignInRepository, ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.signInRepository = skipAuthSignInRepository;
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean isUserIdDuplicate(Long userID) {
        User user = signInRepository.findByUserID(userID);
        return user != null;
    }

    @Override
    public UserActionResponseDTO isUserNickNameDuplicate(String nickName) {
        int nickSize = signInRepository.findByUserNickName(nickName);
        boolean isSuccess;
        String message;

        if(nickSize > 0) {
            isSuccess = false;
            message = "중복된 닉네임 입니다.";
        }else {
            isSuccess = true;
            message = "사용 가능한 닉네임 입니다.";
        }

        return UserActionResponseDTO.builder()
                .isSuccess(isSuccess)
                .message(message)
                .build();
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
    public SignInResponseDTO registerUserResults(Long userId, String nickName, String gender, String ageGroup) {
        SignInResponseDTO responseDTO = new SignInResponseDTO();
        try {
            Boolean isUserSaved = signInRepository.setUserInfo(userId,nickName,gender,ageGroup);

            if (isUserSaved) {
                User user = signInRepository.findByUserID(userId);

                if (user == null){
                    return null;
                }

                responseDTO = SignInResponseDTO.SkipSignIn(
                        user.getUserID(),
                        user.getUserNickName(),
                        user.getUserSex(),
                        user.getUserAgeRange(),
                        user.getIsUserVerified(),
                        String.valueOf(user.getUserJoinDate()));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return  responseDTO;
    }

    @Override
    public SignInResponseDTO registerWithPhoneUserResults(Long userId, PhoneSignInRequestDTO userRequest) {
        SignInResponseDTO responseDTO = new SignInResponseDTO();
        try {
            User userAccount = profileRepository.findExistsAccount(userRequest);


            if(userAccount == null) {
                User user = signInRepository.findByUserID(userId);

                if(user == null) {
                    return null;
                }

                responseDTO = SignInResponseDTO.phoneSignIn(
                        false,
                        user.getUserID(),
                        user.getUserNickName(),
                        user.getUserPhoneSuffix(),
                        user.getUserBirth(),
                        user.getUserSex(),
                        user.getIsUserVerified(),
                        String.valueOf(user.getUserJoinDate()));
            } else {
                responseDTO = SignInResponseDTO.phoneSignIn(
                        true,
                        userAccount.getUserID(),
                        userAccount.getUserNickName(),
                        userAccount.getUserPhoneSuffix(),
                        userAccount.getUserBirth(),
                        userAccount.getUserSex(),
                        userAccount.getIsUserVerified(),
                        String.valueOf(userAccount.getUserJoinDate()));
            }

        }catch (Exception ex) {
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
