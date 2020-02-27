package com.biz.shop.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.shop.domain.CartVO;

public interface CartRepository extends JpaRepository<CartVO, Long> {

}
