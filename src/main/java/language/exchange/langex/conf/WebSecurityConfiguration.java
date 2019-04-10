package language.exchange.langex.conf;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/users/**", "/friends/**", "/chatbox/**", "/chatboxSend/**", "/css/**", "/images/**", "/chatboxLastMessage/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        //https://www.baeldung.com/spri ng-session

        //in order to allow use h2 database
        http.headers().frameOptions().disable();
    }
}
