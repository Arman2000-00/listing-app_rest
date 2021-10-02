package com.example.listingapp_rest.config;

import com.example.listingapp_rest.security.security.JwtAuthenticationEntryPoint;
import com.example.listingapp_rest.security.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PutMapping;

@Configuration
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,

                        "/users/{id}",
                        "/categories",
                        "/categories/{id}",
                        "/listings/{id}",
                        "/listings/byCategory/{categoryId}",
                        "/listings/byUser/{email}"
                ).authenticated()
                .antMatchers(HttpMethod.DELETE, "/users/{id}")
                .hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/listings/{id}",
                        "/categories/{id}")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/users/auth", "/listings","/users")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/listings/{id}", "/categories/{id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll();

        // Custom JWT based security filter
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}