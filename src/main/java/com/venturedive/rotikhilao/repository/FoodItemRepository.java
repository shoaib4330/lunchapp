package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.model.entitiy.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findAllByVendorIdAndStatus(Long vendorId,Short status);

    List<FoodItem> findAllByPriceBetweenAndStatus(Integer fromPrice, Integer toPrice, Short status);

    List<FoodItem> findAllByStatus(Short status);

    List<FoodItem> findAllByVendorId(Long vendorId);

}
