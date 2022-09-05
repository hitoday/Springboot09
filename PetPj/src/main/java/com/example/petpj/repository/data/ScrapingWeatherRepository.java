package com.example.petpj.repository.data;

import com.example.petpj.Entity.data.ScrapingWeather;
import com.example.petpj.service.textTransfer.scWeather;
import org.springframework.data.repository.CrudRepository;

public interface ScrapingWeatherRepository extends CrudRepository<ScrapingWeather, Long> {
}
