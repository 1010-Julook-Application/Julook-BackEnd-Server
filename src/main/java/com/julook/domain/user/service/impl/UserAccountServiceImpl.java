package com.julook.domain.user.service.impl;

import com.julook.domain.user.dto.request.LinkAccountRequestDTO;
import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.dto.request.SMSRequestDTO;
import com.julook.domain.user.dto.response.*;
import com.julook.domain.user.repository.ProfileRepository;
import com.julook.domain.user.service.UserAccountService;
import com.julook.domain.user.entity.User;
import io.swagger.v3.oas.models.links.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final ProfileRepository profileRepository;

    @Override
    public ModifyNickResponseDTO modifiyUserNickname(ModifyNickRequestDTO userRequest) {
        ModifyNickResponseDTO responseDTO = new ModifyNickResponseDTO();
        try{
            Boolean isSuccess = profileRepository.modifyUserNickname(userRequest);
            if(isSuccess) {
                responseDTO.setIsSuccess(true);
                responseDTO.setMessage("닉네임 수정 성공");
                responseDTO.setUserId(userRequest.getUserId());
                responseDTO.setModifiedNick(userRequest.getModifyNickname());
            } else {
                responseDTO.setIsSuccess(false);
                responseDTO.setMessage("닉네임 수정 실패");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccess(false);
            responseDTO.setMessage("DB UPDATE 오류 발생" + ex.getMessage());
            return null;
        }
        return responseDTO;
    }

    @Override
    public DeleteUserResponseDTO deleteUserAccount(Long userId) {
        DeleteUserResponseDTO responseDTO = new DeleteUserResponseDTO();
        String message;
        try {
            Boolean isSuccess = profileRepository.deleteUserAccount(userId);
            if(isSuccess) {
                message = "계정 삭제 완료";
                responseDTO.setUserId(userId.toString());
                responseDTO.setWithdrawDate(LocalDate.now());

            } else{
                message = "계정 삭제 실패 - 요청 바람.";
            }

            responseDTO.setIsSuccess(isSuccess);
            responseDTO.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccess(false);
            responseDTO.setMessage("DB UPDATE 오류 발생" + ex.getMessage());
            return null;
        }
        return responseDTO;
    }

    @Override
    public LinkAccountResponseDTO linkUserAccount(LinkAccountRequestDTO userRequest) {
        LinkAccountResponseDTO responseDTO = new LinkAccountResponseDTO();
        UserActionResponseDTO results = new UserActionResponseDTO();
        try{
            User userAccount = profileRepository.findExistsAccount(userRequest);
            Boolean isSuccess = profileRepository.linkAccount(userAccount, userRequest);

            if((userAccount != null && isSuccess)) {
                results.setIsSuccess(true);
                results.setMessage("Exists : 기존 계정 찾기 성공");

                responseDTO = LinkAccountResponseDTO.builder()
                        .linkedResults(results)
                        .isAlreadyLinked(true)
                        .userId(userAccount.getUserID())
                        .userNickname(userAccount.getUserNickName())
                        .phoneSuffix(userAccount.getUserPhoneSuffix())
                        .isUserVerified(userAccount.getIsUserVerified())
                        .build();
            } else if (userAccount == null && isSuccess) {
                results.setIsSuccess(true);
                results.setMessage("New : 데이터 연동 성공");
                String phoneSfx = userRequest.getPhone().substring(userRequest.getPhone().length() - 4);

                responseDTO = LinkAccountResponseDTO.builder()
                        .linkedResults(results)
                        .isAlreadyLinked(false)
                        .userId(userRequest.getUserId())
                        .userNickname("기존 닉네임 동일")
                        .phoneSuffix(phoneSfx)
                        .isUserVerified("true")
                        .build();
            } else {
                results.setIsSuccess(false);
                results.setMessage("데이터 연동 실패");

                responseDTO = LinkAccountResponseDTO.builder()
                        .linkedResults(results)
                        .build();
            }

        }catch (Exception ex) {
            ex.printStackTrace();
            results.setIsSuccess(false);
            results.setMessage("DB UPDATE 오류 발생" + ex.getMessage());
            responseDTO.setLinkedResults(results);
            return null;
        }
        return responseDTO;
    }

    @Override
    public CheckAccountResponseDTO findMatchedAccount(SMSRequestDTO userRequest) {
        CheckAccountResponseDTO responseDTO;
        List<User> matchedUserList = profileRepository.findExistsAccount(userRequest);

        if(matchedUserList.isEmpty()) {
            responseDTO = CheckAccountResponseDTO.builder()
                    .isAccountExisted(false)
                    .build();
        } else {
            int size = matchedUserList.size();
            List<String> nickList = new ArrayList<>();

            for (User user : matchedUserList) {
                nickList.add(user.getUserNickName());
            }

            responseDTO = CheckAccountResponseDTO.builder()
                    .isAccountExisted(true)
                    .numberOfAccount(size)
                    .userNickName(nickList)
                    .build();
        }
        return responseDTO;
    }
}
