package bside.palmtree.external.oauth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Validated
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "walki.kakao")
public class KakaoClientProperties {
    private final Long appId;
}
