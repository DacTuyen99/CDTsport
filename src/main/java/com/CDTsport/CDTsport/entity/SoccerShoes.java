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
    private String imageId;
    @Type(type = "com.CDTsport.CDTsport.entity.dto.CustomIntegerArrayType")
    private Integer[] list_size;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = SizeSoccerShoes.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "shoes_id",referencedColumnName = "id")
    private List<SizeSoccerShoes> sizeSoccerShoes;

}
