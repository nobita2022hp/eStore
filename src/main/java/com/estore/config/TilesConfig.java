package com.estore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {

    @Bean("viewResolver")
    public ViewResolver getViewResolver(){
        var view = new UrlBasedViewResolver();
        view.setViewClass(TilesView.class);
        return view;
    }

    @Bean("tilesConfigurer")
    public TilesConfigurer getTilesConfigurer(){
        var tile = new TilesConfigurer();
        tile.setDefinitions("/WEB-INF/tiles.xml");
        return tile;
    }
}
