package com.fabriciolfj.github.cartservice.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public WebClient webClient(final ClientRegistrationRepository clientRegistrationRepository, final OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository) {
        final ExchangeStrategies exchangeStrategies = ExchangeStrategies
                .builder().codecs(configurer ->
                        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper)))
                .build();

        final ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, auth2AuthorizedClientRepository);
        oauth2.setDefaultClientRegistrationId("pricing-client");
        return WebClient.builder().apply(oauth2.oauth2Configuration())
                .exchangeStrategies(exchangeStrategies)
                .filter(lbFunction).baseUrl("http://pricing/pricing/price").build();
    }
}
