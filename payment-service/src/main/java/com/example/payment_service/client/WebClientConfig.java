package com.example.payment_service.client;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    public WebClient extraPackageWebClient() {
        return WebClient.builder()
                .baseUrl("http://extrapackage")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    public ExtraPackageClient extraPackageClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(extraPackageWebClient())).build();
        return httpServiceProxyFactory.createClient(ExtraPackageClient.class);

    }


}
