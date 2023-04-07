package top.moma.zoffy.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootConfiguration
@EnableMethodSecurity
public class SecurityConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails admin =
        User.builder()
            .username("admin")
            .password("111111")
            .roles("ADMIN")
            .passwordEncoder(passwordEncoder()::encode)
            .build();
    UserDetails user =
        User.builder()
            .username("user")
            .password("111111")
            .roles("USER")
            .passwordEncoder(passwordEncoder()::encode)
            .build();
    return new InMemoryUserDetailsManager(admin, user);
  }
}
