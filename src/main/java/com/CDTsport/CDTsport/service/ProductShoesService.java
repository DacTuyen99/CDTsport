package com.CDTsport.CDTsport.service;

import com.CDTsport.CDTsport.entity.SizeSoccerShoes;
import com.CDTsport.CDTsport.entity.SoccerShoes;
import com.CDTsport.CDTsport.repository.SizeSoccerShoesRepository;
import com.CDTsport.CDTsport.repository.SoccerShoesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ProductShoesService {
    private final SoccerShoesRepository soccerShoesRepository;
    @Transactional
    public SoccerShoes saveSoccerShoes(SoccerShoes soccerShoes){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        soccerShoes.setTimePost(now);
        if (soccerShoes.getSale() == null) {
            soccerShoes.setSale(0);
            soccerShoes.setPriceSale(soccerShoes.getPrice());
        } else if (soccerShoes.getSale()>0) {
            double a = soccerShoes.getSale()/100.0;
            int price = (int) Math.round(soccerShoes.getPrice()*a);
            soccerShoes.setPriceSale(price);
        }
        return soccerShoesRepository.save(soccerShoes);
    }
}
