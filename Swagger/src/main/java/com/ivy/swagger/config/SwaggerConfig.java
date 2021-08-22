package com.ivy.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.function.Predicate;

@Configuration
//开启Swagger
@EnableSwagger2

public class SwaggerConfig {

    @Bean
    public Docket getDocket(Environment environment) {
        /**
         * 获取当前配置文件
         */
//        String[] profiles = environment.getActiveProfiles();

        /**
         * 设置swagger启动环境
         */
        Profiles profiles = Profiles.of("dev","test");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                /**
                 * 配置Swagger信息
                 */
                .apiInfo(getApiInfo())
                /**
                 * 配置是否开启Swagger, true开启/false关闭
                 */
                .enable(flag)

                .select()
                /**
                 * RequestHandlerSelectors 配置要扫描接口的方式
                 *      1.any 扫描全部
                 *      2.basePackage 指定要扫描的包
                 *      3.none 都不扫描
                 *      4.withClassAnnotation 扫描类上的注解
                 *      5.withMethodAnnotation 扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                /**
                 * PathSelectors 扫描过滤
                 *      1.ant 只扫描 "/" 路径下的请求
                 */
                .paths(PathSelectors.ant("/"))
                .build();
    }

    //配置Swagger信息 = infoApi
    private ApiInfo getApiInfo() {
        //作者信息
        Contact contact = new Contact("余晖ivySunny", "https://github.com/ivy-Sunny", "thelife_dream@iclou.com");
        return new ApiInfo(
                "ivySunny Swagger notes.",
                "long long ago.",
                "v1.0",
                "http://localhost:8080/hello",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
