package com.kienast.apiservice.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable()
		.cors().and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.PUT, "/auth").permitAll()
		.antMatchers(HttpMethod.PATCH, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/auth").permitAll()
		.antMatchers(HttpMethod.POST, "/auth/{\\d+}").permitAll()
		.antMatchers(HttpMethod.PUT, "/auth/{\\d+}").permitAll()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
		.antMatchers(HttpMethod.GET, "/app/{\\d+}").permitAll()
		.antMatchers(HttpMethod.GET, "/appOfUser/{\\d+}").permitAll()	
		.antMatchers(HttpMethod.GET, "/app/{\\d+}/{\\\\d+}").permitAll()
		.antMatchers(HttpMethod.POST, "/app/{\\d+}/{\\\\d+}").permitAll()
		.antMatchers(HttpMethod.POST, "/app").permitAll()
		.antMatchers(HttpMethod.PUT, "/app").permitAll()
		.antMatchers(HttpMethod.GET, "/app").permitAll()
		.antMatchers(HttpMethod.POST, "/mfa/verify").permitAll()
		.antMatchers(HttpMethod.POST, "/mfa/setup").permitAll()
		.antMatchers(HttpMethod.POST, "/auth/auth").permitAll()
		//Matchers for the Frontend
		.antMatchers(HttpMethod.PUT, "/api/auth").permitAll()
		.antMatchers(HttpMethod.PATCH, "/api/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/api/auth").permitAll()
		.antMatchers(HttpMethod.POST, "/api/auth/{\\d+}").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/auth/{\\d+}").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
		.antMatchers(HttpMethod.GET, "/api/auth/app/{\\d+}").permitAll()
		.antMatchers(HttpMethod.GET, "/api/appOfUser/{\\d+}").permitAll()	
		.antMatchers(HttpMethod.GET, "/api/app/{\\d+}/{\\\\d+}").permitAll()
		.antMatchers(HttpMethod.POST, "/api/app/{\\d+}/{\\\\d+}").permitAll()
		.antMatchers(HttpMethod.POST, "/api/app").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/app").permitAll()
		.antMatchers(HttpMethod.GET, "/api/app").permitAll()
		.antMatchers(HttpMethod.POST, "/api/mfa/verify").permitAll()
		.antMatchers(HttpMethod.POST, "/api/mfa/setup").permitAll();
		//.antMatchers("/**").authenticated();
	// @formatter:on
	}
	
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
