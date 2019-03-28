package com.syshlang.injection.config;



import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringComponentProvider;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.syshlang"})
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {
        register(RequestContextFilter.class);
        packages("com.syshlang");
        register(JacksonFeature.class);
        register(RequestContextFilter.class);
        register(SpringComponentProvider.class);
        register(DataSourceConfig.class);
    }
}
