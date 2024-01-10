package com.CDTsport.CDTsport.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "product_img")
@AllArgsConstructor
@Builder
public class ProductImage {
    @Id
    @SequenceGenerator(name = "product_img_sequence",sequenceName = "product_img_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_img_sequence")
    private Long id;
    private String name;
    private String type;
    private String urlImg;
}
