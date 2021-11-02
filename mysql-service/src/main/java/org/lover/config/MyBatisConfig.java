package org.lover.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.lover.mapper")
public class MyBatisConfig {


}
