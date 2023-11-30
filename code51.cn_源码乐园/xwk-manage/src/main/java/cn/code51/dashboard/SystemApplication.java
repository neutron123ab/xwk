package cn.code51.dashboard;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class SystemApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SystemApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        String property = environment.getProperty("server.port");
        System.out.println("http://loclahost:"+property);

    }



}
