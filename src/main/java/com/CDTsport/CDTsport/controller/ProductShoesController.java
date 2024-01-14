package com.CDTsport.CDTsport.controller;

import com.CDTsport.CDTsport.entity.SoccerShoes;
import com.CDTsport.CDTsport.entity.dto.SoccerShoesPageDTO;
import com.CDTsport.CDTsport.service.ProductShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductShoesController {
    private final ProductShoesService productShoesService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/shoes")
    public ResponseEntity<SoccerShoes> postProductShoes(@RequestBody SoccerShoes soccerShoes){
        return ResponseEntity.ok(productShoesService.saveSoccerShoes(soccerShoes));
    }
    @GetMapping ("/shoes/{id}")
    public ResponseEntity<SoccerShoes> getShoesById(@PathVariable Long id){
        return ResponseEntity.ok(productShoesService.getSoccerShoesById(id));
    }
    @GetMapping("/shoes")
    public ResponseEntity<SoccerShoesPageDTO> getSearchShoes(@RequestParam(required = false) String description,
                                                             @RequestParam(required = false) int page,
                                                             @RequestParam(required = false) int size){
        return ResponseEntity.ok(productShoesService.getSearchSoccerShoes(description,page,size));
    }
}
