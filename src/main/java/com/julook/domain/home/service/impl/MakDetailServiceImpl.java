package com.julook.domain.home.service.impl;

import com.julook.domain.home.dto.MakInUserActionDTO;
import com.julook.domain.home.dto.MakInfoWithUserActionDTO;
import com.julook.domain.home.dto.response.MakDetailResponseDTO;
import com.julook.domain.home.dto.response.MakLikesAndCommentsDTO;
import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.repository.MakDetailRepository;
import com.julook.domain.home.service.MakDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


@Service
public class MakDetailServiceImpl implements MakDetailService {
    private final MakDetailRepository makDetailRepository;
    private final ModelMapper modelMapper;

    public MakDetailServiceImpl(MakDetailRepository makDetailRepository, ModelMapper modelMapper) {
        this.makDetailRepository = makDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MakDetailResponseDTO getMakDetailInfo(int makNumber, long userId) {
        MakInfoWithUserActionDTO makWithUser = makDetailRepository.getMakDetailInfo(makNumber, userId);

        // "makAwards" 값을 컴마(,)로 분리하여 리스트에 추가
        List<String> awards = new ArrayList<>();
        if (makWithUser.getMakInfo().getMakAwards() != null) {
            String[] awardParts = makWithUser.getMakInfo().getMakAwards().split(",");
            for (String award : awardParts) {
                String trimmedAward = award.trim();
                if (!trimmedAward.isEmpty()) {
                    awards.add(trimmedAward);
                }
            }
        }

        return createMakDetailResponseDTO(makWithUser.getMakInfo(), awards, makWithUser.getUserAction());
    }

    @Override
    public MakLikesAndCommentsDTO getMakLikesAndComments(int makNumber) {
        MakLikesAndCommentsDTO makLikesAndComment = makDetailRepository.getMakLikesAndComments(makNumber);
        return makLikesAndComment;
    }

    private MakDetailResponseDTO.MainDetail createMainDetail(MakInfo makInfo) {
        return MakDetailResponseDTO.MainDetail.builder()
                .makAlcoholPercentage(makInfo.getMakAlcoholPercentage())
                .makVolume(makInfo.getMakVolume())
                .makPrice(makInfo.getMakPrice())
                .build();
    }

    private MakDetailResponseDTO.Taste createTaste(MakInfo makInfo) {
        return MakDetailResponseDTO.Taste.builder()
                .makTasteSweet(makInfo.getMakTasteSweet())
                .makTasteSour(makInfo.getMakTasteSour())
                .makTasteThick(makInfo.getMakTasteThick())
                .makTasteFresh(makInfo.getMakTasteFresh())
                .build();
    }

    private MakDetailResponseDTO.BreweryInfo createBreweryInfo(MakInfo makInfo) {
        return MakDetailResponseDTO.BreweryInfo.builder()
                .makBrewery(makInfo.getMakBrewery())
                .makBreweryLink(makInfo.getMakBreweryLink())
                .makSalesLink(makInfo.getMakSalesLink())
                .build();
    }

    private MakDetailResponseDTO createMakDetailResponseDTO(MakInfo makInfo, List<String> awards, MakInUserActionDTO makInUser) {
        List<MakDetailResponseDTO.MainDetail> mainDetails = Collections.singletonList(createMainDetail(makInfo));
        List<MakDetailResponseDTO.Taste> tastes = Collections.singletonList(createTaste(makInfo));
        List<MakDetailResponseDTO.BreweryInfo> breweryInfoList = Collections.singletonList(createBreweryInfo(makInfo));

        return MakDetailResponseDTO.builder()
                .makSeq(makInfo.getMakSeq())
                .makType(makInfo.getMakType())
                .makName(makInfo.getMakName())
                .makImageNumber(makInfo.getMakImageNumber())
                .userAction(makInUser)
                .attributes(Collections.singletonList(
                        MakDetailResponseDTO.Attribute.builder()
                                .mainDetail(mainDetails)
                                .taste(tastes)
                                .makContent(makInfo.getMakContent())
                                .makAwards(awards)
                                .makRaw(makInfo.getMakRaw())
                                .breweryInfo(breweryInfoList)
                                .build()
                ))
                .build();
    }
}

