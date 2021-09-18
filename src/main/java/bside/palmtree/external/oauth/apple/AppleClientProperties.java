package bside.palmtree.external.oauth.apple;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Validated
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "walki.apple")
public class AppleClientProperties {
	private final String baseUrl;
	private final String serviceId;
	private final String teamId;
	private final String keyId;
	private final String keyPath;
}
