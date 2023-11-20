package com.julook.domain.home.service.impl;

import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.common.dto.PageableInfoDTO;
import com.julook.domain.home.dto.response.FindByUserResponseDTO;
import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.repository.FindByUserRepository;
import com.julook.domain.home.service.FindByUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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


    public Page<MakInfoDTO> findMakWithPaginationAndSorting(int offset, int pageSize, String sort, List<String> categories) {
        Sort.Direction condition;
        if ("makSeq".equals(sort)) {
            condition = Sort.Direction.DESC;
        } else if ("highAlcohol".equals(sort)) {
            condition = Sort.Direction.DESC;
            sort = "makAlcoholPercentage";
        } else {
            condition = Sort.Direction.ASC;
            sort = "makAlcoholPercentage";
        }

        // 카테고리별 Specification 조건 초기화
        Specification<MakInfo> specifications = null;

        // 카테고리별 Specification 조건 추가
        for (String category : categories) {
            Specification<MakInfo> categorySpecification = null;
            switch (category) {
                case "sweet":
                    categorySpecification = (root, query, builder) ->
                            builder.between(root.get("makTasteSweet"), 3, 5);
                    break;
                case "thick":
                    categorySpecification = (root, query, builder) ->
                            builder.between(root.get("makTasteThick"), 3, 5);
                    break;
                case "noAspartame":
                    categorySpecification = (root, query, builder) ->
                            builder.notLike(root.get("makRaw"), "%아스파탐%");
                    break;
                case "sour":
                    categorySpecification = (root, query, builder) ->
                            builder.between(root.get("makTasteSour"), 3, 5);
                    break;
                case "fresh":
                    categorySpecification = (root, query, builder) ->
                            builder.between(root.get("makTasteFresh"), 3, 5);
                    break;
                default:
                    // 다 선택 안한 경우 조건을 걸지 않음
                    continue;
            }

            if (categorySpecification != null) {
                if (specifications == null) {
                    specifications = Specification.where(categorySpecification);
                } else {
                    specifications = specifications.and(categorySpecification);
                }
            }
        }



        Page<MakInfo> result = findByUserRepository.findAll(specifications, PageRequest.of(offset, pageSize).withSort(
                Sort.by(condition, sort)));

        Page<MakInfoDTO> makInfoDTOList = result.map(entity -> modelMapper.map(entity, MakInfoDTO.class));

        return makInfoDTOList;
    }


    private List<MakInfoDTO> convertToDTOList(List<MakInfo> entities) {
        List<MakInfoDTO> dtoList = new ArrayList<>();
        for (MakInfo entity : entities) {
            MakInfoDTO dto = modelMapper.map(entity, MakInfoDTO.class);
            dtoList.add(dto);
        }
        return dtoList;
    }

}
