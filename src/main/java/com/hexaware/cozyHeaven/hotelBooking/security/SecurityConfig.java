//package com.hexaware.cozyHeaven.hotelBooking.security;
//
//import java.util.Collections;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//
//import com.hexaware.cozyHeaven.hotelBooking.filter.JwtAuthFilter;
//
//
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//	
//	@Autowired
//	JwtAuthFilter authFilter;
//	
//    @Bean
//    //authentication
//    public UserDetailsService userDetailsService() {
//       return new UserInfoUserDetailsService();
//    }
//    
//    @Bean
//    public  SecurityFilterChain   getSecurityFilterChain(HttpSecurity http) throws Exception {
//    	
//    		return http.csrf().disable()
//    				//.cors(Customizer.withDefaults())
//    			.authorizeHttpRequests().requestMatchers("/users/registration/new","/guest/bookings/{guestId}","/api/guest/hotels","/users/login/authenticate","/swagger-ui/**","/v3/api-docs/**", "/swagger-resource/**", "/webjars/**","/api/admin/**","/api/admin/**","/api/admin/users/**","/api/admin/owners/**","/api/admin/hotels/**","/api/users/getbyid/**","/api/users/delete/**","/owner/**","/owner/rooms/**","/owner/bookings/{bookingID}/**","/owner/**","/api/guest/hotels","/api/admin/hotels/**")
//    			.permitAll()
//    			.and()
//    			.authorizeHttpRequests()
//    			.requestMatchers("/guest/bookings/{guestId}").hasRole("GUEST")
//    			.requestMatchers("/guest/**").hasRole("GUEST")
//    			.requestMatchers("/api/users/**").hasRole("GUEST")
//    			.requestMatchers("/owner/**").hasRole("OWNER")
//    			.requestMatchers("/admin/**").hasRole("ADMIN")
//    			.anyRequest().authenticated().and()   //.formLogin().and().build();
//    			.sessionManagement()
//    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//    			.and()
//    			.authenticationProvider(authenticationProvider())
//    			.addFilterBefore(authFilter	, UsernamePasswordAuthenticationFilter.class)
//    			.build();
//    	
//    		// CORS configuration, enable CORS
//            http.cors().configurationSource(new CorsConfigurationSource() {
//                @Override
//                public CorsConfiguration getCorsConfiguration(HttpServletRequest request)
//                { CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                    config.setAllowedMethods(Collections.singletonList("*"));
//                    config.setAllowCredentials(true);
//                    config.setAllowedHeaders(Collections.singletonList("*"));
//                    config.setMaxAge(3600L); return config;
//                }
//            });
//    		
//    }
//
//    @Bean    
//    public PasswordEncoder passwordEncoder() {          
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    
//    
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//    	
//    	return config.getAuthenticationManager();
//    	
//    }
//    
//    
//    
//}


package com.hexaware.cozyHeaven.hotelBooking.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hexaware.cozyHeaven.hotelBooking.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter authFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .cors() 
            .and()
            .authorizeHttpRequests()
                .requestMatchers(
                //	"/guest/**",
                	// "/guest/hotels?location=Ooty",
                    "/users/registration/new", 
                  //  "/guest/bookings/{guestId}", 
                  //  "/api/guest/hotels", 
                    "/users/login/authenticate", 
                    "/users/userId",
                    "/swagger-ui/**", 
                    "/v3/api-docs/**", 
                    "/swagger-resource/**", 
                    "/webjars/**", 
                    "/api/admin/**", 
                    "/api/admin/users/**", 
                    "/api/admin/owners/**", 
                    "/api/admin/hotels/**", 
                    "/api/users/getbyid/**", 
                    "/api/users/delete/**" )
//                    "/owner/**", 
//                    "/owner/rooms/**", 
//                    "/owner/bookings/{bookingID}/**")
                .permitAll()
               // .requestMatchers("/guest/bookings/{guestId}").hasRole("GUEST")
                .requestMatchers("/guest/**").hasRole("GUEST")
                .requestMatchers("/api/users/**").hasAnyRole("GUEST", "ADMIN","OWNER")
                .requestMatchers("/owner/**").hasRole("OWNER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
