package com.example.package_command.infrastructure.config;

import com.example.package_command.adapters.in.client.ExtraFeatureClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpExchangeAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    public WebClient extraFeatureWebClient(){
        return WebClient.builder().baseUrl("http://extrafeatures").filter(loadBalancedExchangeFilterFunction).build();
    }

    @Bean
    public ExtraFeatureClient extraFeatureClient(){

        HttpServiceProxyFactory httpServiceProxyFactory=HttpServiceProxyFactory.builderFor(WebClientAdapter.create(extraFeatureWebClient())).build();
        return httpServiceProxyFactory.createClient(ExtraFeatureClient.class);
    }


}
