package kr.go.ngii.edu.config.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import kr.go.ngii.edu.main.users.mapper.UserRoleMapper;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.model.UserRole;
import kr.go.ngii.edu.main.users.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationProvider authProvider;

	@Autowired
	CustomLoginSuccessHandler loginSuccessHandler;

	@Autowired
	CustomLoginFailureHandler loginFailureHandler;

	@Autowired
	CustomLogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	CustomAccessDeniedHandler accessDeniedHandler;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authProvider);
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/join").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/introduce/**").permitAll()
				.antMatchers("/gallary/**").permitAll()
				.antMatchers("/surport/**").permitAll()
				.antMatchers("/api/v1/**").permitAll()
				.antMatchers("/cm-admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/course/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
				.antMatchers("/storymap/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
				.antMatchers("/map/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login_process")
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
				.permitAll()
			.and()
				.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
			.and()
				.csrf()
				.disable();
	}


	@Component
	public class CustomAuthenticationProvider implements AuthenticationProvider {

		@Autowired
		private UserService userService;

		@Autowired
		private UserRoleMapper roleMapper;


		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {

			String principal = authentication.getPrincipal().toString();
			String credentials = authentication.getCredentials().toString();

			User user = userService.get(principal);

			if (user == null || user.getIdx() == null) {
				return null;
			}

			List<UserRole> userRoles = roleMapper.list(user.getIdx());

			if (new BCryptPasswordEncoder().matches(credentials, user.getPassword())) {
				return new UsernamePasswordAuthenticationToken(principal, credentials, userRoles);
			} else {
				return null;
			}
		}

		@Override
		public boolean supports(Class<?> authentication) {
			return authentication.equals(UsernamePasswordAuthenticationToken.class);
		}
	}


	@Component
	public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

		@Autowired
		private UserService userService;

		@Override
		public void onAuthenticationSuccess(
				HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {

			super.onAuthenticationSuccess(request, response, authentication);

//			setDefaultTargetUrl("/course");
//			setDefaultTargetUrl("/");

			User user = userService.get(authentication.getName());
			user.setPassword(null);

			request.getSession().setAttribute("USER_INFO", user);
			request.getSession().setAttribute("USER_IDX", user.getIdx());
			request.getSession().setAttribute("USER_ID", user.getUserid());

			response.setStatus(HttpStatus.SC_OK);
		}
	}

	@Component
	public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) 
				throws IOException, ServletException {

			setDefaultFailureUrl("/login?error");

			super.onAuthenticationFailure(request, response, exception);

			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception.getMessage());
		}
	}

	@Component
	public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
				throws IOException, ServletException {

			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, null);

//			response.sendRedirect(request.getContextPath() + "/login");
			response.sendRedirect(request.getContextPath() + "/");
			response.setStatus(HttpStatus.SC_OK);
		}
	}

	@Component
	public class CustomAccessDeniedHandler implements AccessDeniedHandler {

		@Override
		public void handle(
				HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) 
						throws IOException, ServletException {

			response.sendRedirect(request.getContextPath() + "/errorFobridden");
			response.setStatus(HttpStatus.SC_FORBIDDEN);
		}

	}

}
