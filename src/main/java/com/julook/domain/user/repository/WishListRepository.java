package com.julook.domain.user.repository;

import com.julook.domain.user.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long>, WishListRepositoryCustom {

}
