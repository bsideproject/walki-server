package bside.palmtree.external.oauth.kakao;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = {KakaoClientProperties.class})
@Configuration
public class KakaoConfig {

}
