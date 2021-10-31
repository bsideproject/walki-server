package bside.palmtree.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import bside.palmtree.external.oauth.apple.AppleClientProperties;
import bside.palmtree.external.oauth.kakao.KakaoClientProperties;

@EnableConfigurationProperties(value = {AppleClientProperties.class, KakaoClientProperties.class})
@Configuration
public class ExternalConfig {
}
