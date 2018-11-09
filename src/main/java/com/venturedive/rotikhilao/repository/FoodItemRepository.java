package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.FoodItem;
import com.venturedive.rotikhilao.entitiy.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
//
//    List<FoodItem> findAllByVendorAndStatus(Vendor vendor,Short status);
//
//    List<FoodItem> findAllByPriceBetweenAndStatus(Integer fromPrice, Integer toPrice, Short status);
//
//    List<FoodItem> findAllByStatus(Short status);

    List<FoodItem> findAllByVendorId(Long vendorId);

    @Query(value = "select f from FoodItem f  where  f.dtCreated = current_date  and f.quantity > 0 and f.vendorId = :vendorId")
    List<FoodItem> getMenuForTodayByVendorId(@Param("vendorId") long vendorId);

    Optional<FoodItem> findByFoodItemIdAndQuantityGreaterThanAndVendor(Long id, Integer quantity, Vendor vendor);
}
