package br.eti.krebscode.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_teste_id";
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos", "/produtos/",
			"/categorias", "/categorias/"
	};

	private static final String[] AUTHENTICATED_MATCHERS_GET = {
			"/pagamentos", "/pagamentos/"
	};
	
	private static final String[] ADMIN_MATCHERS_ALL = {
			"/users/**",
			"/produtos/**",
			"/categorias/**"
	};
	
	private static final String[] ADMIN_OR_CLIENT_MATCHERS_POST_PUT_DELETE = {
			"/pagamentos", "/pagamentos/", "/pagamentos/**"
	};
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http
//                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() // acesso livre, sem login
                .antMatchers(HttpMethod.GET, AUTHENTICATED_MATCHERS_GET).authenticated() // qualquer um que esteja logado
                .antMatchers(ADMIN_MATCHERS_ALL).hasRole("ADMIN") 
                .antMatchers(HttpMethod.POST, ADMIN_OR_CLIENT_MATCHERS_POST_PUT_DELETE).hasAnyRole("ADMIN","CLIENT")
                .antMatchers(HttpMethod.PUT, ADMIN_OR_CLIENT_MATCHERS_POST_PUT_DELETE).hasAnyRole("ADMIN","CLIENT")
                .antMatchers(HttpMethod.DELETE, ADMIN_OR_CLIENT_MATCHERS_POST_PUT_DELETE).hasAnyRole("ADMIN","CLIENT")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
	}
	
}
