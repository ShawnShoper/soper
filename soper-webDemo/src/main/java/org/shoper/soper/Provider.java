package org.shoper.soper;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ShawnShoper on 16/6/23.
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.shoper.soper")
@Configuration
@EnableAutoConfiguration
public class Provider extends SpringBootServletInitializer
        implements
        EmbeddedServletContainerCustomizer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(Provider.class);
    }

    public static void main(String[] args) throws Exception {
        // String name = ManagementFactory.getRuntimeMXBean().getName();
        // System.out.println(name);
        ConfigurableApplicationContext web = new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.CONSOLE).sources(Provider.class)
                .web(true).run(args);
        web.registerShutdownHook();
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8081);
    }

}
