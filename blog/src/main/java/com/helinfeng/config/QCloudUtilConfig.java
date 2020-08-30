package com.helinfeng.config;

import com.helinfeng.util.QCloudUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QCloudUtilConfig {
    @ConfigurationProperties(prefix = "spring.qcloud")
    @Bean
    public QCloudUtil qcloudCosUtils() {
        return new QCloudUtil();
    }
}
