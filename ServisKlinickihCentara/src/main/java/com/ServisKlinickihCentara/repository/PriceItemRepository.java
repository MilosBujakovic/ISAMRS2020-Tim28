package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.clinics.PriceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceItemRepository extends JpaRepository<PriceItem,Long> {

    List<PriceItem> findAll();
}
