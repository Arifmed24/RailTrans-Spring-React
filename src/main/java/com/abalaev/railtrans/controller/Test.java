package com.abalaev.railtrans.controller;

import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Timetable;
import com.abalaev.railtrans.service.api.RouteTimetablesService;
import com.abalaev.railtrans.service.api.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class Test {
    @Autowired
    RouteTimetablesService routeTimetablesService;
    @Autowired
    TimetableService timetableService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView doT(){
        Date dateBegin = null;
        Date dateEnd = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateBegin = sdf.parse("2016-10-29 00:00:00");
            dateEnd = sdf.parse("2016-09-15 23:50:55");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------");
        System.out.println("ROUTE TIMETABLES");
        System.out.println("----------------------------------------");
        List<RouteTimetables> routeTimetablesInPeriod = routeTimetablesService.getRouteTimetablesInPeriod(dateBegin, dateEnd);
        for (RouteTimetables r: routeTimetablesInPeriod) {
            System.out.println(r);
        }
        System.out.println("----------------------------------------");
        System.out.println("TIMETABLES");
        System.out.println("----------------------------------------");
        Set<Timetable> timetableListFromRouteTimetables = timetableService.getTimetableListFromRouteTimetables(routeTimetablesInPeriod);
        for (Timetable t: timetableListFromRouteTimetables){
            System.out.println(t);
        }
        return new ModelAndView();
    }
}
