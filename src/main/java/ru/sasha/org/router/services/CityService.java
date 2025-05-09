package ru.sasha.org.router.services;

import org.springframework.stereotype.Service;
import ru.sasha.org.router.model.City;
import ru.sasha.org.router.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public City findCityByNAme(String cityTame){
        return cityRepository.findByCityName(cityTame);
    }
}
