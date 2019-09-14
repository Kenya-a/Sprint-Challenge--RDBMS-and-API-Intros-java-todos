package com.lambdaschool.javatodos.config;

//import org.apache.catalina.Server;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile("dev")
public class ServerConfiguration
{
    @Value("${h2.tcp.port:3000}")
    private String h2TpcPort;

    @Value("${h2.web.port:8000}")
    private String h2WebPort;

    @Bean
    @ConditionalOnExpression("${h2.web.enabled:true}")
    public Server h2WebServer() throws SQLException
    {
        return Server.createWebServer("-web", "-webAllow", "-webPort", "h2WebPort").start();
    }

}
