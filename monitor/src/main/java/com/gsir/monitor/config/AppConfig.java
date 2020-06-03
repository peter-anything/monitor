package com.gsir.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.gsir.monitor.service.impl.UserServiceImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
@ComponentScan(basePackages="com.gsir.monitor", excludeFilters = { @Filter(value = Controller.class) })
public class AppConfig {

    @Bean(destroyMethod = "close")
    public DruidDataSource dataSource(DBConfig dbConfig) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriverClassName());
        dataSource.setUrl(dbConfig.getUrl());
        dataSource.setUsername(dbConfig.getUsername());
        dataSource.setPassword(dbConfig.getPassword());

        return dataSource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DruidDataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean(); 
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);

        return mybatisSqlSessionFactoryBean;
    }
    
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DruidDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        return dataSourceTransactionManager;
    }

    @Bean
    public UserServiceImpl userService() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        
        return userServiceImpl;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.gsir.monitor.mapper");

        return mapperScannerConfigurer;
        
    }
    
}
