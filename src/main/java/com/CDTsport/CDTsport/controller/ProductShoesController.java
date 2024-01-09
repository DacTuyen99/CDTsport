package com.CDTsport.CDTsport.controller;

import com.CDTsport.CDTsport.entity.SoccerShoes;
import com.CDTsport.CDTsport.service.ProductShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post-product")
public class ProductShoesController {
    private final ProductShoesService productShoesService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/shoes")
    public ResponseEntity<SoccerShoes> postProductShoes(@RequestBody SoccerShoes soccerShoes){
        return ResponseEntity.ok(productShoesService.saveSoccerShoes(soccerShoes));
    }

}
