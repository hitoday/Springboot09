package com.example.petpj.Entity.data;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//0830
public class ScrapingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "No")
    private Long seq;

    @Column(name = "Local")
    private String cityName;

    @Column(name = "confirmed_num")
    private String confirmed;

    @Column(name = "increase_num")
    private String increase_num;
}
