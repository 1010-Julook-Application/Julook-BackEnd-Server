package com.julook.global.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@OpenAPIDefinition(info = @Info(title = "Julook Makgeolli API", description = "주룩 막걸리 API 목록입니다.", version = "1.0"))
@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {
}
