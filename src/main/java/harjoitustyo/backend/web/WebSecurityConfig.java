package harjoitustyo.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



//Määrittelee turvallisuusasetukset ja miten security toimii 
@Configuration
@EnableMethodSecurity(securedEnabled=true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailService userDetailService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( authorize-> authorize
            .anyRequest().authenticated()
            )
            .formLogin(
                formLogin -> formLogin.defaultSuccessUrl("/etusivu",true).permitAll()) // palauttaa etusivulle kirjautumisen jälkeen
            .logout(logout -> logout.permitAll());
        return http.build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
