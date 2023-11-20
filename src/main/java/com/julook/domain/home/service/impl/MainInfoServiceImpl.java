package com.julook.domain.home.service.impl;

import com.julook.domain.common.dto.response.MakResponseDTO;
import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.dto.CommentInfoDTO;
import com.julook.domain.home.dto.response.RecentCommentsResponseDTO;
import com.julook.domain.home.repository.MainInfoRepository;
import com.julook.domain.home.service.MainInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainInfoServiceImpl implements MainInfoService {
    private final MainInfoRepository mainInfoRepository;
    private final ModelMapper modelMapper;

    public MainInfoServiceImpl(MainInfoRepository mainInfoRepository, ModelMapper modelMapper) {
        this.mainInfoRepository = mainInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MakResponseDTO> getNewMakList() {
        List<MakInfo> searchResults = mainInfoRepository.getNewMakList();

        List<MakResponseDTO> responseDTO = searchResults.stream()
                .map(makInfo -> {
                    // MainDetail 객체 생성 및 설정
                    MakResponseDTO.MainDetail mainDetail = MakResponseDTO.MainDetail.builder()
                            .makAlcoholPercentage(makInfo.getMakAlcoholPercentage())
                            .makPrice(makInfo.getMakPrice())
                            .makVolume(makInfo.getMakVolume())
                            .build();

                    // Taste 객체 생성 및 설정
                    MakResponseDTO.Taste taste = MakResponseDTO.Taste.builder()
                            .makTasteSweet(makInfo.getMakTasteSweet())
                            .makTasteSour(makInfo.getMakTasteSour())
                            .makTasteThick(makInfo.getMakTasteThick())
                            .makTasteFresh(makInfo.getMakTasteFresh())
                            .build();

                    MakResponseDTO dto = MakResponseDTO.builder()
                            .makNumber(makInfo.getMakSeq())
                            .makName(makInfo.getMakName())
                            .makType(makInfo.getMakType())
                            .makImageNumber(makInfo.getMakImageNumber())
                            .mainDetail(mainDetail)
                            .taste(taste)
                            .build();


                    return dto;
                })
                .collect(Collectors.toList());

        return responseDTO;

    }

    @Override
    public List<RecentCommentsResponseDTO> getRecentComments() {
        List<RecentCommentsResponseDTO> results = mainInfoRepository.getRecentComments();

        List<RecentCommentsResponseDTO> responseDTOList = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            RecentCommentsResponseDTO responseDTO = RecentCommentsResponseDTO.builder()
                    .makNumber(results.get(i).getMakNumber())
                    .makName(results.get(i).getMakName())
                    .makImageNumber(results.get(i).getMakImageNumber())
                    .commentInfo(CommentInfoDTO.builder()
                            .userNickName(results.get(i).getCommentInfo().getUserNickName())
                            .userLikeOrNot(results.get(i).getCommentInfo().getUserLikeOrNot())
                            .contents(results.get(i).getCommentInfo().getContents())
                            .writeDate(results.get(i).getCommentInfo().getWriteDate())
                            .build())
                    .build();

            responseDTOList.add(responseDTO);
        }

        return responseDTOList; // 생성된 DTO 리스트를 반환
    }


}
