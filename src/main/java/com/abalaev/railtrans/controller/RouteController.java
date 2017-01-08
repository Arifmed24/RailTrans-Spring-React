package com.abalaev.railtrans.controller;

import com.abalaev.railtrans.model.*;
import com.abalaev.railtrans.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Controller
@CrossOrigin
public class RouteController {

    private final RouteTimetablesService routeTimetablesService;
    private final RouteService routeService;
    private final StationService stationService;
    private final TrainService trainService;
    private final TimetableService timetableService;

    @Autowired
    public RouteController(RouteTimetablesService routeTimetablesService, RouteService routeService, StationService stationService, TrainService trainService, TimetableService timetableService) {
        this.routeTimetablesService = routeTimetablesService;
        this.routeService = routeService;
        this.stationService = stationService;
        this.trainService = trainService;
        this.timetableService = timetableService;
    }

    @RequestMapping(value = "/route/", method = RequestMethod.GET)
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        if (routes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @RequestMapping(value = "/route/{id}/graphic/", method = RequestMethod.GET)
    public ResponseEntity<List<RouteTimetables>> getNewGraphicForm(@PathVariable("id") int id) {
        Route route = routeService.readRoute(id);
        List<RouteTimetables> routeTimetables = routeTimetablesService.createTemplateOfGraphic(route);
        if (routeTimetables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(routeTimetables, HttpStatus.OK);
    }

    @RequestMapping(value = "/route/{id}/graphic/", method = RequestMethod.POST)
    public ResponseEntity<Void> getNewGraphic(@PathVariable("id") int id, @RequestBody List<RouteTimetables> routeTimetablesList, UriComponentsBuilder ucBuilder) {
        Route route = routeService.readRoute(id);
        routeTimetablesService.createGraphic(routeTimetablesList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/route/{id}/graphic/").buildAndExpand(route.getIdRoute()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    static class NewRouteResponse {
        public List<Station> stations;
        public List<Train> trains;
    }

    @RequestMapping(value = "/route/new/", method = RequestMethod.GET)
    public ResponseEntity<NewRouteResponse> getNewRouteForm() {
        List<Station> stations = stationService.getAllStations();
        List<Train> trains = trainService.getAllTrains();
        NewRouteResponse routeResponse = new NewRouteResponse();
        routeResponse.stations = stations;
        routeResponse.trains = trains;
        return new ResponseEntity<>(routeResponse, HttpStatus.OK);
    }

    static class RouteWithElementsResponse {
        public Route route;
        public int elements;
        public List<Station> stations;
    }

    static class NewRouteRequest {
        public Train train;
        public Station startStation;
        public Station finishStation;
        public String routeName;
        public int elements;
    }

    @RequestMapping(value = "/route/new/", method = RequestMethod.POST)
    public ResponseEntity<RouteWithElementsResponse> getNewRoute(@RequestBody NewRouteRequest routeRequest) throws IOException {
        Train train = routeRequest.train;
        Station startStation = routeRequest.startStation;
        Station finishStation = routeRequest.finishStation;
        String routeName = routeRequest.routeName;
        int elements = routeRequest.elements;
        List<Station> stations = stationService.getAllStations();
        Route route = new Route();
        route.setRouteName(routeName);
        route.setTrain(train);
        route.setStartStation(startStation);
        route.setFinishStation(finishStation);
        route = routeService.createRoute(route);
        RouteWithElementsResponse routeWithElementsResponse = new RouteWithElementsResponse();
        routeWithElementsResponse.route = route;
        routeWithElementsResponse.elements = elements;
        routeWithElementsResponse.stations = stations;
        return new ResponseEntity<>(routeWithElementsResponse, HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/route/new/elements", method = RequestMethod.GET)
//    public ModelAndView fillNewRouteForm(HttpServletRequest request){
//        int numberOfStations = (int) request.getSession().getAttribute("stationsInRoute");
//        Route route = (Route) request.getSession().getAttribute("route");
//        List<Station> stations = stationService.getAllStations();
//        request.getSession().setAttribute("stations", stations);
//        List<RouteTimetables> graphicOfRoute = new ArrayList<>();
//        for (int i = 0; i < numberOfStations-1; i++) {
//            RouteTimetables r = new RouteTimetables();
//            graphicOfRoute.add(r);
//        }
//        request.setAttribute("title", "Fill new route");
//        request.setAttribute("route", route);
//        request.getSession().setAttribute("number", numberOfStations-1);
//        request.getSession().setAttribute("elements", graphicOfRoute);
//        return new ModelAndView("/admin/route/fill-route");
//    }

    static class FillRouteRequest {
        Route route;
        List<Station> routeStations;
        List<String> stringDates;

    }

    @RequestMapping(value = "/route/new/fill", method = RequestMethod.POST)
    public ResponseEntity<Void> fillNewRoute(@RequestBody FillRouteRequest routeRequest, UriComponentsBuilder ucBuilder) throws Exception {
        Route route = routeRequest.route;
        List<Station> routeStations = routeRequest.routeStations;
        List<String> stringDates = routeRequest.stringDates;
        List<Timetable> routeTimetables = new ArrayList<>();

        for (int i = 0; i < routeStations.size() - 1; i++) {
            Timetable newTimetable = timetableService.readByStations(routeStations.get(i), routeStations.get(i + 1));
            routeTimetables.add(newTimetable);
        }
        List<Date> routeDates = routeTimetablesService.checkDatesInRoute(stringDates);
        for (int i = 0; i < routeTimetables.size(); i++) {
            RouteTimetables newRoutetimetable = new RouteTimetables();
            newRoutetimetable.setNumberInRoute(i + 1);
            newRoutetimetable.setLine(routeTimetables.get(i));
            newRoutetimetable.setRouteId(route);
            newRoutetimetable.setFreeSeats(route.getTrain().getSeats());
            newRoutetimetable.setDateDeparture(routeDates.get(i * 2));
            newRoutetimetable.setDateArrival(routeDates.get(i * 2 + 1));
            routeTimetablesService.createRoutetimetable(newRoutetimetable);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
