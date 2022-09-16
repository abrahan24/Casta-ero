package uap.usic.siga.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import uap.usic.siga.servicios.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AccessDeniedHandler accessDeniedHandler() {
	        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
	        accessDeniedHandler.setErrorPage("/accessDenied.htm");
	        return accessDeniedHandler;
	    }

	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(authenticationProvider());
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	         http
	                .authorizeRequests().antMatchers("/questionTypes/admin", "/users/signupForm", "/users/formNuevoUsuario",  "/users/addAdminForm").hasRole("ADMIN")
	                .and()
	                .authorizeRequests().antMatchers("/wallPage", "/questions/add", "/questionTypes", "/profile/*", "/answers/**","/comprobante/openFile/{id}","/comprobante/inicioComprobantes","/comprobanteIngresos/**","/prestamos/**","/devoluciones/**","/aCarpetas/**","/aEstantes/**",
	                        "/porMesGestion/**","/porTipoPago/**","/ingresos/**","/gastos/**","/reportes/**").hasAnyRole("ADMIN", "USER")
	                .and()
	                .authorizeRequests().antMatchers("/login", "/users/signup", "/resource/**").permitAll()
	                .and()
	                .formLogin().loginPage("/login").usernameParameter("usuario").passwordParameter("password").permitAll()
	                .loginProcessingUrl("/doLogin")
	                .successForwardUrl("/postLogin")
	                .failureUrl("/loginFailed")
	                .and()
	                .logout().logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll()
	                .and()
	                .csrf().disable();
	    }
}
