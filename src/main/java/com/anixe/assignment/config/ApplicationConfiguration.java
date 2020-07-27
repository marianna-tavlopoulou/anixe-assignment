package com.anixe.assignment.config;

import org.springframework.context.annotation.Configuration;



@Configuration
public class ApplicationConfiguration {

    //    @Bean
    //    @ConfigurationProperties(prefix = "spring.datasource")
    //    public DataSource dataSource() {
    //        DataSourceBuilder<HikariDataSource> dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
    //        dataSourceBuilder.username("SA");
    //        dataSourceBuilder.password("");
    //        return dataSourceBuilder.build();
    //    }
    //
    //    @Bean
    //    @ConfigurationProperties(prefix = "spring.datasource.liquibase")
    //    public SpringLiquibase liquibase() {
    //        SpringLiquibase liquibase = new SpringLiquibase();
    //        liquibase.setDataSource(dataSource());
    //        return liquibase;
    //    }
}
