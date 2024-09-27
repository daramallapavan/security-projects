package com.example.Spring_Security_KeyCloack_Aug.keyCloack;

import com.example.Spring_Security_KeyCloack_Aug.config.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityKeyClackConfig {

    private final JwtAuthConverter jwtAuthConverter;

/*@Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf( csrfSpec -> csrfSpec.disable())
                .authorizeExchange(auth->auth.pathMatchers( "/api/demo" ).permitAll()
                        .anyExchange().authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
      return   httpSecurity.build();


    }*/


@Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf( csrfSpec -> csrfSpec.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers( "/api/demo" ).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer( oauth2->oauth2.jwt(jwt->jwt.jwtAuthenticationConverter( jwtAuthConverter )) );

      return   httpSecurity.build();


    }
}
