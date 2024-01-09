package com.CDTsport.CDTsport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "size_soccer_shoes")
@AllArgsConstructor
public class SizeSoccerShoes {
    @Id
    @SequenceGenerator(name = "size_soccer_shoes_sequence",sequenceName = "size_soccer_shoes_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "size_soccer_shoes_sequence")
    private Long id;
    private Integer sizeShoes;
    private Integer quantity;
}
