/**
 * 
 */
package com.javateam.myBatisTransactionSample.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author javateam
 *
 */
public class AppInitializer 
			extends AbstractAnnotationConfigDispatcherServletInitializer 
			implements WebApplicationInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
		// ex) return new Class<?>[]{ ServiceConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] { WebConfig.class };
	}

	@Override
    protected String[] getServletMappings() {

		return new String[]{ "/" };
        // ex) return new String[] { "*.html", "*.json", "*.do" };
    }
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext ctx 
			= new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        ctx.setServletContext(servletContext);
         
        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                "appServlet", new DispatcherServlet(ctx));
        
        // POST 방식 한글 처리 encoding filter
        servletContext.addFilter("name", new CharacterEncodingFilter("UTF-8"))
        			  .addMappingForUrlPatterns(null, false, "/*");
 
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
	} //
	
}