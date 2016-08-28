/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.ustream.ustreamtest.client.config;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tv.ustream.ustreamtest.client.model.OptionBuilder;

/**
 *
 * @author pfeiferlaszlo
 */
@Configuration
public class Config {
    
    @Bean
    public OptionBuilder optionBuilder() {
        return new OptionBuilder();
    }
    
    @Bean
    public CommandLineParser commandLineParser() {
        return new DefaultParser();
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
