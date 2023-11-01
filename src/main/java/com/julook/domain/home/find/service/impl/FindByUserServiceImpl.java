package com.julook.domain.home.find.service.impl;

import com.julook.domain.home.find.dto.MakInfoDTO;
import com.julook.domain.home.find.dto.PageableInfoDTO;
import com.julook.domain.home.find.dto.response.FindByUserResponseDTO;
import com.julook.domain.home.find.entity.MakInfo;
import com.julook.domain.home.find.repository.FindByUserRepository;
import com.julook.domain.home.find.service.FindByUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindByUserServiceImpl implements FindByUserService {
    private final FindByUserRepository findByUserRepository;
    private final ModelMapper modelMapper;


    public FindByUserServiceImpl(FindByUserRepository findByUserRepository, ModelMapper modelMapper) {
        this.findByUserRepository = findByUserRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public FindByUserResponseDTO getMakInfoListByUserPreferences(Long lastMakNum, List<String> categories, String sort, Pageable pageable) {
        List<MakInfo> makInfoList = findByUserRepository.findByPreferences(lastMakNum, categories, sort, pageable);

        List<MakInfoDTO> makInfoDTOList = convertToDTOList(makInfoList);

        int totalCount = makInfoList.get(0).getMakSeq().intValue();
        int nextCursor = makInfoList.isEmpty() ? 0 : makInfoList.get(makInfoList.size() - 1).getMakSeq().intValue();

        List<PageableInfoDTO> pageData = new ArrayList<>();
        PageableInfoDTO pageInfo = new PageableInfoDTO();
        pageInfo.setCurrentPage(pageable.getPageNumber()+1);
        pageInfo.setSize(pageable.getPageSize());
        pageInfo.setFirst(pageable.getPageNumber() == 0);
        pageInfo.setLast(makInfoList.size() < pageable.getPageSize());
        pageInfo.setTotalMakElements(totalCount);
        pageInfo.setTotalPages((int) Math.ceil((double) totalCount / pageable.getPageSize()+1));
        pageData.add(pageInfo);

        FindByUserResponseDTO responseDTO = FindByUserResponseDTO.builder()
                .contents(makInfoDTOList)
                .totalCount(totalCount)
                .nextCursor(nextCursor)
                .pageInfo(pageData)
                .build();

        return responseDTO;
    }

    /**
     * 엔터티를 DTO로 변환하는 메서드
     *
     * @param entities 엔터티 리스트
     * @return DTO 리스트
     */
    private List<MakInfoDTO> convertToDTOList(List<MakInfo> entities) {
        List<MakInfoDTO> dtoList = new ArrayList<>();
        for (MakInfo entity : entities) {
            MakInfoDTO dto = modelMapper.map(entity, MakInfoDTO.class);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
