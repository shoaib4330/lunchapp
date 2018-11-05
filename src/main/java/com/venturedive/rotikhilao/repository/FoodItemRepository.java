package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.FoodItem;
import com.venturedive.rotikhilao.entitiy.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
//
//    List<FoodItem> findAllByVendorAndStatus(Vendor vendor,Short status);
//
//    List<FoodItem> findAllByPriceBetweenAndStatus(Integer fromPrice, Integer toPrice, Short status);
//
//    List<FoodItem> findAllByStatus(Short status);

    List<FoodItem> findAllByVendorId(Long vendorId);

}
