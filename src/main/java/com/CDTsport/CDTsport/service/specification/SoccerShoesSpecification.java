package com.CDTsport.CDTsport.service.specification;

import com.CDTsport.CDTsport.entity.SoccerShoes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
@RequiredArgsConstructor
public class SoccerShoesSpecification {
    public Specification<SoccerShoes> containDescription(final String description){
        return (Root<SoccerShoes> root, CriteriaQuery<?> query, CriteriaBuilder cb)->{
            if (description == null || description.trim().isEmpty()){
                return null;
            }
            return cb.like(cb.upper(root.get("description")),"%"+description.toUpperCase()+"%");
        };
    }
}
