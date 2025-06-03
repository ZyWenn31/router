package ru.sasha.org.router.util;

import org.springframework.stereotype.Component;
import ru.sasha.org.router.model.City;
import ru.sasha.org.router.model.Flight;
import ru.sasha.org.router.repository.FlightRepository;

import java.time.LocalDateTime;
import java.util.*;


public class RouteFinder {

    private Map<City, List<Flight>> graph;

    // Конструктор принимает список городов и список рейсов (уже отфильтрованных по типу)
    public RouteFinder(List<City> cities, List<Flight> flights) {
        graph = new HashMap<>();
        for (City city : cities) {
            graph.put(city, new ArrayList<>());
        }
        for (Flight flight : flights) {
            graph.get(flight.getDepartureCity()).add(flight);
        }
    }

    public List<List<Flight>> findRoutes(City startCity, City endCity, LocalDateTime departure){
        List<List<Flight>> buff = findAllRoutes(startCity, endCity);
        if (buff.isEmpty()){
            return buff;
        }
        List<List<Flight>> result = new ArrayList<>(buff);
        result.forEach(results -> results.sort(Comparator.comparing(Flight::getDeparture)));
        for (List<Flight> flights : buff){
            if (flights.getFirst().getDeparture().isBefore(departure)){
                result.remove(flights);
            } else{
                if (flights.size() != 1) {
                    for (int i = 0; i < flights.size() - 1; i++) {
                        Flight thisFlight = flights.get(i);
                        Flight nextFlight = flights.get(i + 1);

                        if (thisFlight.getArrival().isAfter(nextFlight.getDeparture())) {
                            result.remove(flights);
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    // Поиск всех маршрутов без дополнительной фильтрации по типу
    private List<List<Flight>> findAllRoutes(City startCity, City endCity) {
        List<List<Flight>> allRoutes = new ArrayList<>();
        findRoutesDFS(startCity, endCity, new ArrayList<>(), allRoutes);
        return allRoutes;
    }

    private void findRoutesDFS(City currentCity, City endCity, List<Flight> currentRoute, List<List<Flight>> allRoutes) {
        if (currentCity.equals(endCity)) {
            allRoutes.add(new ArrayList<>(currentRoute));
            return;
        }

        for (Flight flight : graph.get(currentCity)) {
            if (!currentRoute.contains(flight)) {
                currentRoute.add(flight);
                findRoutesDFS(flight.getArrivslCity(), endCity, currentRoute, allRoutes);
                currentRoute.remove(currentRoute.size() - 1); // шаг назад
            }
        }
    }
}
