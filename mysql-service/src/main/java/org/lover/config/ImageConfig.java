package org.lover.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("storage.image")
@Getter
@Setter
public class ImageConfig {
    private  String savePath;
    private List<String>allowType;

}
