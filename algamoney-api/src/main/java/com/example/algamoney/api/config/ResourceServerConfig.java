package com.example.algamoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	// admin em base64 --> YWRtaW4=
	// Basic <usuario>:<senha>
	// Basic admin:admin
	// Basic YWRtaW46YWRtaW4=
	
	/*
	 * Não é mais necessário
	 * @Autowired private UserDetailsService userDetailsService;
	 */

// Não é mais necessário
//	@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROLE");
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.authorizeRequests()
			.antMatchers("/categorias").permitAll()
			.anyRequest().authenticated().and()
			.httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
				.disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}

//	Não é mais necessário
//	@Bean 
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}


}
