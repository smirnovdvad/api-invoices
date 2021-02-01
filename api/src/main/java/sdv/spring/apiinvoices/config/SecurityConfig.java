package sdv.spring.apiinvoices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sdv.spring.apiinvoices.security.ApiInvoicePasswordEncoderFactories;
import sdv.spring.apiinvoices.security.RestHeaderAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("DMITRY")
        .password("{bcrypt}$2a$10$nDOF7MwrdI2Qbgk77.IRbuRuISyZyg5UyfiJEzIFpIEJascNRG51K")
        .roles("ADMIN");
    }

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter( new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class).csrf().disable();
                //.authorizeRequests(authorize ->{
                //    authorize.antMatchers("/api/v1/**").hasRole("ADMIN");

        //}).authorizeRequests()
         //       .and()
          //      .csrf().disable();

        http.authorizeRequests(
                authorize ->{
                    authorize.antMatchers("/api/v1/**").hasRole("ADMIN")
                            .antMatchers("/h2-console/**").permitAll();

                }
        ).authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();

        //h2 console
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return ApiInvoicePasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
