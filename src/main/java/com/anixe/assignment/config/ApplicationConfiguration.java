package com.anixe.assignment.config;

import com.anixe.assignment.logging.RestLogFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ApplicationConfiguration {
    public static final String HOTEL_REST_URL = "/api/hotels";
    public static final String BOOKING_REST_URL = "/api/bookings";

    @Value("${loggingProperties.isDebug}")
    private boolean isDebug;


    @Bean
    public FilterRegistrationBean<RestLogFilter> restLogFilter() {
        FilterRegistrationBean<RestLogFilter> registrationBean = new FilterRegistrationBean<>();
        RestLogFilter restLogFilter = new RestLogFilter(isDebug);
        registrationBean.setFilter(restLogFilter);
        return registrationBean;
    }
}
