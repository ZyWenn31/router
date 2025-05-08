package ru.sasha.org.router.services;

import org.springframework.stereotype.Service;
import ru.sasha.org.router.repository.CityRepository;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
