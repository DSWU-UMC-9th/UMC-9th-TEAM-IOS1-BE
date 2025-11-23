package com.example.fog.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("2025-UMC-9th-TEAM-IOS1") //이름
                .description("향수 리뷰 & 추천 사이트"); //설명

        Server prodServer = new Server().url("https://www.tenma.store");

        return new OpenAPI()
                .info(info)
                .servers(List.of(prodServer))
                .components(new Components().addSecuritySchemes(
                        SECURITY_SCHEME_NAME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ))
                // 전역 보안: 모든 API 호출 시 토큰 요구
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));

    }

}
