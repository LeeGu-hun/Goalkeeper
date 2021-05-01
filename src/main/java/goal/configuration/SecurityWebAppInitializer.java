package goal.configuration;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer{
	public SecurityWebAppInitializer() {
		super(SecurityConfig.class);
	}

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		// TODO Auto-generated method stub
		super.insertFilters(servletContext, new CustomSpringSecurityFilterChain());
	}
	
	
}
