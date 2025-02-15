package com.turkcell.user_command.infrastructure.config;

import com.turkcell.user_command.adapters.in.client.ExtraPackageClient;
import com.turkcell.user_command.adapters.in.client.PackageClient;
import com.turkcell.user_command.adapters.in.client.ShakeWinClient;
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
    public WebClient packageWebClient() {

        return WebClient.builder().baseUrl("http://package-query").filter(loadBalancedExchangeFilterFunction).build();
    }


    @Bean
    public PackageClient packageClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(packageWebClient())).build();
        return httpServiceProxyFactory.createClient(PackageClient.class);

    }

    @Bean
    public WebClient extraPackageWebClient() {
        return WebClient.builder().baseUrl("http://extrapackage").filter(loadBalancedExchangeFilterFunction).build();
    }

    @Bean
    public ExtraPackageClient extraPackageClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(extraPackageWebClient())).build();
        return httpServiceProxyFactory.createClient(ExtraPackageClient.class);
    }

    @Bean
    public WebClient shakeWinWebClient() {
        return WebClient.builder().baseUrl("http://shakewin-service").filter(loadBalancedExchangeFilterFunction).build();
    }

    @Bean
    public ShakeWinClient shakeWinClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(shakeWinWebClient())).build();
        return httpServiceProxyFactory.createClient(ShakeWinClient.class);
    }

}
