package com.CDTsport.CDTsport.entity.dto;

import com.CDTsport.CDTsport.entity.SoccerShoes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoccerShoesPageDTO {
    private Long total = 0L;
    private Integer currentPage;
    private Integer totalPage;
    private List<SoccerShoes> list = new ArrayList<>();
}
