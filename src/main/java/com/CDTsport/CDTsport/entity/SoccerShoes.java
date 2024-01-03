package com.CDTsport.CDTsport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "soccer_shoes")
@AllArgsConstructor
public class SoccerShoes {
    @Id
    @SequenceGenerator(name = "soccer_shoes_sequence",sequenceName = "soccer_shoes_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soccer_shoes_sequence")
    private Long id;
    private String brand;
    private String description;
    private Timestamp timePost;
    private Integer price;
    private Integer sale;
    private Integer priceSale;
    @Type(type = "com.CDTsport.CDTsport.entity.IntArrayUserType")
    private Integer[] imgId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoesId",insertable = false,updatable = false)
    private List<SizeSoccerShoes> sizeSoccerShoes;

}
