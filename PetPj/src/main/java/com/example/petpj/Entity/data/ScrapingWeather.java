package com.example.petpj.Entity.data;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ScrapingWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long seq;

    @Column
    private String hour;

    @Column
    private String weather;

    @Column
    private String temp;

    @Column
    private String sensible_temp;
}
