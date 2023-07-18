package de.neuefische.backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Value("${CLOUD_NAME}")
    private String cloudName;
    @Value("${API_KEY}")
    private String apiKey;
    @Value("${API_SECRET}")
    private String apiSecret;


    public String getCloudName() {
        return cloudName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", getCloudName());
        config.put("api_key", getApiKey());
        config.put("api_secret", getApiSecret());

        return new Cloudinary(config);
    }
}