package com.abalaev.railtrans.configuration;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc //разрешает проект использовать MVC
@Configuration // данный класс является Java Configuration
@ComponentScan(basePackages = "com.abalaev.railtrans", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
}) //где искать компоненты проекта.
@Import({HibernateConfiguration.class}) //импорт Security
public class AppConfig {

}


