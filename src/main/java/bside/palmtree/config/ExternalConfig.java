package bside.palmtree.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import bside.palmtree.external.oauth.apple.AppleClientProperties;

@EnableConfigurationProperties(value = {AppleClientProperties.class})
@Configuration
public class ExternalConfig {
}
