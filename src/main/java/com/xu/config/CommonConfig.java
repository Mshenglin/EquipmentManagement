package com.xu.config;

import com.xu.util.ThreadPoolUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置线程池
 * @author Alkmg
 */
@Configuration
public class CommonConfig {
    @Bean
    public ThreadPoolUtil getThreadPoolUtil(){
        return new ThreadPoolUtil(ThreadPoolUtil.ThreadEnum.FixedThread,50);
    }
}
