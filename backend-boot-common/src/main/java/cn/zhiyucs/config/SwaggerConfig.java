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
        return GroupedOpenApi.builder().group("智能仓库管理")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("zhiyu1998");

        return new OpenAPI().info(new Info()
                .title("智能仓库管理")
                .description("智能仓库管理")
                .contact(contact)
                .version("1.0.0")
                .termsOfService("175.178.215.113")
                .license(new License().name("MIT")
                        .url("暂无")));
    }

}