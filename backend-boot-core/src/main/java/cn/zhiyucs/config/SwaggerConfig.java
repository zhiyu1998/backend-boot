package cn.zhiyucs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 *
 * @author zhiyu1998
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"cn.zhiyucs"};
        return GroupedOpenApi.builder().group("BackendBoot")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("zhiyu1998");

        return new OpenAPI().info(new Info()
                .title("Backend-Boot")
                .description("Backend-Boot")
                .contact(contact)
                .version("1.0.0")
                .termsOfService("https://github.com/zhiyu1998/backend-boot/tree/main")
                .license(new License().name("MIT")
                        .url("https://github.com/zhiyu1998/backend-boot/tree/main")));
    }

}