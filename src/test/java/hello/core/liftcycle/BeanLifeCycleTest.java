package hello.core.liftcycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // 닫는 메서드
    }

    @Configuration
    static class LifeCycleConfig {
        // destroyMethod 의 추론 기능은 'close','shutdown'이라는 이름의 메서드를 자동으로 호출해준다.
        // 이름 그대로 종료 메서드를 추론해서 호출해준다.
        //destroyMethod 를 = "" 처럼 공백으로 해주면 추론메소드가 동작하지않는다.
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
