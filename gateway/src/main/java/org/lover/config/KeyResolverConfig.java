package org.lover.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfig {

    @Bean
    @Primary
    public KeyResolver hostAddrKeyResolver()
    {
        return exchange->Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

    @Bean
    public  KeyResolver uriKeyResolver()
    {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
