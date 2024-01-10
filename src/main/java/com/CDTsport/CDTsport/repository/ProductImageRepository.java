package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}
