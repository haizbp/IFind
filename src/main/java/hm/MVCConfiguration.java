package hm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import hm.annotation.handle.CustomRequestMappingHandlerMapping;

@Configuration
public class MVCConfiguration  extends WebMvcConfigurationSupport {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType")
				.defaultContentType(MediaType.ALL).ignoreAcceptHeader(true).useJaf(false)
				.mediaType("json", MediaType.APPLICATION_JSON);

	}

	@Override
	protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
		return new CustomRequestMappingHandlerMapping(Helper.API_PATTERN);
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(localeChangeInterceptor());
//	}

//	@Bean
//	public LocaleChangeInterceptor localeChangeInterceptor() {
//		LocaleChangeInterceptor lci = new LocaleChangeInterceptorCustom();
//		lci.setParamName("lang");
//		return lci;
//	}

	@Bean(name = "textTemplateEngine")
	public TemplateEngine textTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(textTemplateResolver());
		return templateEngine;
	}

	private ITemplateResolver textTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/**");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

}
