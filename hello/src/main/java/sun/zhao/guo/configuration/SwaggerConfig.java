package sun.zhao.guo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.configuration
 * @Author: SzgStart-sunzg
 * @Date: 2024/01/26/00:34
 * @Description:
 */

@Slf4j
//@EnableKnife4j
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("本地测试系统")
                        .version("1.0")
                        .description( "Knife4j集成springdoc-openapi示例")
                        .termsOfService("http://doc.test.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.test.com"))
                );
    }

}
