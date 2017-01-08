package com.abalaev.railtrans.presentation;

import com.abalaev.railtrans.configuration.AppConfig;
import com.abalaev.railtrans.dao.api.RouteTimetablesDao;
import com.abalaev.railtrans.dao.impl.RouteTimetablesDaoImpl;
import com.abalaev.railtrans.model.RouteTimetables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//
//import com.abalaev.railtrans.configuration.AppConfig;
//import com.abalaev.railtrans.model.RoleEnum;
//import com.abalaev.railtrans.model.User;
//import com.abalaev.railtrans.service.api.UserService;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//


//@ContextConfiguration(classes=AppConfig.class)
public class Main {
@Autowired
    public RouteTimetablesDao routeTimetablesDao;



    public static void main(String[] args) {

        /**
         * Check geting list of route timetables in some period
         */
        AbstractApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);

        Main main = ctx.getBean(Main.class);

        Date dateBegin = null;
        Date dateEnd = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateBegin = sdf.parse("2016-10-29 00:00:00");
            dateEnd = sdf.parse("2016-09-15 23:50:55");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<RouteTimetables> routeTimetablesInPeriod = main.routeTimetablesDao.getRouteTimetablesInPeriod(dateBegin, dateEnd);
        for (RouteTimetables r: routeTimetablesInPeriod) {
            System.out.println(r);
        }


    }
}
