package org.velasquez.springbootbackendpetlovers.autenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.velasquez.springbootbackendpetlovers.autenticacion.filter.JwtAuthenticationFilter;
import org.velasquez.springbootbackendpetlovers.autenticacion.filter.JwtValidationFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(authz ->
                authz
                        .requestMatchers(HttpMethod.GET, "/apiAutenticacion/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/apiAutenticacion/usuarioCliente").permitAll()
                        .requestMatchers(HttpMethod.GET, "/apiCliente/clientes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/apiCliente/mascotas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/apiAdmin/clientes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/apiAdmin/updateCliente{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/apiAdmin/deleteCliente{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/apiAutenticacion/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/apiAutenticacion/usuarios/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/apiAutenticacion/usuarios/{}").hasAnyRole("CLIENTE","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/apiAutenticacion/roles").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .cors(cors->cors.configurationSource(configurationSource()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(this.configurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }

}
