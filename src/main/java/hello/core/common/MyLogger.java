package hello.core.common;

import org.springframework.context.annotation.Scope;
<<<<<<< HEAD
import org.springframework.context.annotation.ScopedProxyMode;
=======
>>>>>>> origin/master
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

    @Component
<<<<<<< HEAD
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    // proxyMode = ScopedProxyMode.TARGET_CLASS 는 가짜 프록시 클래스를 주입시킨다.
    // Provider을 사용하든, 프록시를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점

=======
    @Scope(value = "request")
>>>>>>> origin/master
    public class MyLogger {

        private String uuid;
        private String requestURL;

        public void setRequestURL(String requestURL) {
            this.requestURL = requestURL;
        }

        public void log(String message) {
            System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);

        }

        @PostConstruct
        public void init() {
            uuid = UUID.randomUUID().toString();
            System.out.println("[" + uuid + "] request scope bean create: " + this );

        }

        @PreDestroy
        public void close() {
            System.out.println("[" + uuid + "] request scope bean close: " + this );

        }
    }
