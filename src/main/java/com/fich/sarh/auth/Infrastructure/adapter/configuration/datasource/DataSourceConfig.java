package com.fich.sarh.auth.Infrastructure.adapter.configuration.datasource;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.prod")
    public DataSourceProperties prodDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "prodDataSource")
    public DataSource prodDataSource(@Qualifier("prodDataSourceProperties")
                                     DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.test")
    public DataSourceProperties testDataSourceProperties(){
        return new DataSourceProperties();
    }



    @Bean(name = "testDataSource")
    public DataSource testDataSource(@Qualifier("testDataSourceProperties")
                                      DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("prodDataSource") DataSource prodDataSource,
                                 @Qualifier("testDataSource")DataSource testDataSource){

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> targets = new HashMap<>();
        targets.put("PROD", prodDataSource);
        targets.put("TEST", testDataSource);

       // RoleBasedRoutingDataSource routing = new RoleBasedRoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(prodDataSource);
        routingDataSource.setTargetDataSources(targets);
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }


}
