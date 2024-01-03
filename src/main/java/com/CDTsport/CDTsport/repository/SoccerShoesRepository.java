package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.SoccerShoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SoccerShoesRepository extends JpaRepository<SoccerShoes,Long> {

}
