package goal.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{

	@Value("${'C:\\uploadfile'}")
	private String path;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/group-image/**")
        .addResourceLocations("file:///C:/group-image/");
        registry.addResourceHandler("/profile/**")
        .addResourceLocations("file:///C:/profile/")
        .setCachePeriod(20);
		registry.addResourceHandler(path + "/**") 
		.addResourceLocations("file:" + path + "/"); // 디렉토리 경로 (반드시 file: 을 붙여주어야 한다.)
	}

}
