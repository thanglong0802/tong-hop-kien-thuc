package com.dagoras.edu.api.config;

import com.dagoras.edu.api.entity.User;
import com.dagoras.edu.api.jwt.CustomUserDetails;
import com.dagoras.edu.api.jwt.JwtAuthenticationFilter;
import com.dagoras.edu.api.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserRepository userRepository, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST, "/user", "/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/student").hasAnyAuthority("ADMIN", "TEACHER")
                .antMatchers(HttpMethod.POST, "/student/dev").hasAnyAuthority("ADMIN", "TEACHER")
                .antMatchers(HttpMethod.POST, "/student/search").permitAll()
                .antMatchers(HttpMethod.PUT, "/student").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH).hasAnyAuthority("ADMIN")
                .antMatchers("/voucher-details").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(
                        ((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        })
                );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username);
                CustomUserDetails customUserDetails = new CustomUserDetails();
                if (ObjectUtils.allNull(user)) {
                    throw new UsernameNotFoundException("User " + username + " Not Found");
                }
                customUserDetails.setUsername(user.getUsername());
                customUserDetails.setPassword(user.getPassword());
                customUserDetails.setEmail(user.getEmail());
                customUserDetails.setRole(user.getRole());
                return customUserDetails;
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
