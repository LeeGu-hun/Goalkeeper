package goal.configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/group-image/**")
        .addResourceLocations("file:///C:/group-image/")
        .setCachePeriod(20);
		registry.addResourceHandler("/board-image/**")
        .addResourceLocations("file:///C:/board-image/")
        .setCachePeriod(20);
		
	}
	

}
