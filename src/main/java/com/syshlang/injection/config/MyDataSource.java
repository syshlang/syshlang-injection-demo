package com.syshlang.injection.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Qualifier;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MyDataSource {
    @Bean(name = "myDataSource")
    @Primary
    @ConfigurationProperties(prefix="druid")
    public DataSource oneDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "myJdbcTemplate")
    public JdbcTemplate oneJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
