package dori.sns.back.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {

	@Value("${front.server.url}")
	private String frontServerUrl;
	@Value("${back.server.url}")
	private String ssoServerUrl;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var corsConfiguration = new CorsConfiguration();
        // http메소드 허용
        corsConfiguration
                .setAllowedMethods(List.of(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));

        // 헤더
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);

        // 오리진 서버 허용
        corsConfiguration.addAllowedOrigin(frontServerUrl);
        corsConfiguration.addAllowedOrigin(ssoServerUrl);

        // Credential 허용
        corsConfiguration.setAllowCredentials(true);

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
   
}
