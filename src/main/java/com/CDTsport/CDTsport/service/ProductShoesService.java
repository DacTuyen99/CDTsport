package com.CDTsport.CDTsport.service;

import com.CDTsport.CDTsport.entity.SizeSoccerShoes;
import com.CDTsport.CDTsport.entity.SoccerShoes;
import com.CDTsport.CDTsport.entity.dto.SoccerShoesPageDTO;
import com.CDTsport.CDTsport.exception.BaseException;
import com.CDTsport.CDTsport.repository.SizeSoccerShoesRepository;
import com.CDTsport.CDTsport.repository.SoccerShoesRepository;
import com.CDTsport.CDTsport.service.specification.SoccerShoesSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductShoesService {
    private final SoccerShoesRepository soccerShoesRepository;
    private final SoccerShoesSpecification soccerShoesSpecification;
    @Transactional
    public SoccerShoes saveSoccerShoes(SoccerShoes soccerShoes) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        soccerShoes.setTimePost(now);
        if (soccerShoes.getSale() == null) {
            soccerShoes.setSale(0);
            soccerShoes.setPriceSale(soccerShoes.getPrice());
        } else if (soccerShoes.getSale() > 0) {
            double a = soccerShoes.getSale() / 100.0;
            int price = (int) Math.round(soccerShoes.getPrice() * a);
            soccerShoes.setPriceSale(price);
        }
        return soccerShoesRepository.save(soccerShoes);
    }

    public SoccerShoes getSoccerShoesById(Long id) {
        Optional<SoccerShoes> soccerShoesOptional = soccerShoesRepository.findSoccerShoesById(id);
        if (!soccerShoesOptional.isPresent()) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Not found");
        }
        return soccerShoesOptional.get();
    }

    public SoccerShoesPageDTO getSearchSoccerShoes(String description, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "timePost"));
        Page<SoccerShoes> soccerShoesPageDTOPage = soccerShoesRepository.findAll(
                Specification.where(soccerShoesSpecification.containDescription(description))
                , pageRequest
        );
        return SoccerShoesPageDTO.builder()
                .currentPage(page)
                .list(soccerShoesPageDTOPage.toList())
                .total(soccerShoesPageDTOPage.getTotalElements())
                .totalPage(soccerShoesPageDTOPage.getTotalPages())
                .build();
    }

    public SoccerShoesPageDTO findByBrand(String brand, Integer pr1, Integer pr2, int page) {
        if (pr1 == null && pr2 == null) {
            pr1 = 0;
            pr2 = 10000000;
        }
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "time_post"));
        Page<SoccerShoes> listShoes = soccerShoesRepository.findAllByBrand(brand, pr1, pr2, pageable);
        return SoccerShoesPageDTO.builder()
                .totalPage(listShoes.getTotalPages())
                .total(listShoes.getTotalElements())
                .currentPage(page)
                .list(listShoes.getContent())
                .build();
    }
}
