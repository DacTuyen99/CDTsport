package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.SoccerShoes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SoccerShoesRepository extends JpaRepository<SoccerShoes,Long> , JpaSpecificationExecutor<SoccerShoes> {
    Optional<SoccerShoes> findSoccerShoesById(Long id);
    @Query(value = "select * from soccer_shoes where brand = ?1 and price between ?2 and ?3",
            countQuery = "select count(*) from soccer_shoes where brand = ?1 and price between ?2 and ?3"
            ,nativeQuery = true)
    Page<SoccerShoes> findAllByBrand(String brand,Integer pr1,Integer pr2, Pageable pageable);
}
