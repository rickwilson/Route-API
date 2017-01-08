package core;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.plugins.EC2Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/example/**", "/", "/docs/**", "/widget/v2/**", "/open_api/v1/post/json/request_quote", "/open_api/v1/post/json/insure_order", "/open_api/v1/post/json/update_tracking", "/open_api/v1/get/json/currency_codes", "/open_api/v1/get/json/order_types", "/open_api/v1/get/json/payment_types", "/open_api/v1/get/json/shipping_carrier_codes", "/limelight/v2/add/insurance", "/limelight/v2/subscription", "/konnektive/v2/add/order", "/konnektive/v2/add/account/json", "/publicHealth/health/a7d|s8$fgs!98fg3*sdg4569s5gh|5gs*sf").permitAll()
                .antMatchers("/health/**").hasAuthority("SYSTEM_DEVELOPER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }

    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard().withPlugin(new EC2Plugin());
        AWSXRay.setGlobalRecorder(builder.build());
    }

}
