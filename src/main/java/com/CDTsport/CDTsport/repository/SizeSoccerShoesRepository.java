package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.SizeSoccerShoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SizeSoccerShoesRepository extends JpaRepository<SizeSoccerShoes,Long> {

}
