package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.SoccerShoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SoccerShoesRepository extends JpaRepository<SoccerShoes,Long> , JpaSpecificationExecutor<SoccerShoes> {
    Optional<SoccerShoes> findSoccerShoesById(Long id);

}
