package com.myhub.traffic.dark.horse.configuration;

import com.myhub.traffic.dark.horse.Request;
import com.myhub.traffic.dark.horse.starter.RequestEventConsumerStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class TrafficDarkHorseAutoConfiguration {
    @Primary
    @Bean
    @ConditionalOnMissingBean
    public Request request() {
        return new Request();
    }

    @Primary
    @Bean
    @ConditionalOnMissingBean
    public RequestEventConsumerStarter requestEventConsumerStarter() {
        return new RequestEventConsumerStarter();
    }
}
