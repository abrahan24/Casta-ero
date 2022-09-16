package uap.usic.siga.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;


@Configuration
public class TilesConfig {

	 @Bean
	    public UrlBasedViewResolver tilesViewResolver() {
	        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
	        tilesViewResolver.setViewClass(org.springframework.web.servlet.view.tiles3.TilesView.class);
	        tilesViewResolver.setOrder(-2);
	        return tilesViewResolver;
	    }

	    @Bean
	    public TilesConfigurer tilesConfigurer() {
	        TilesConfigurer tilesConfigurer = new TilesConfigurer();
	        tilesConfigurer.setDefinitions("/WEB-INF/tiles/definitions/tile-definition.xml");
	        return tilesConfigurer;
	    }
}
