package hello.core.liftcycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    // 스프링 빈의 이벤트 라이프 사이클
    // 스프링컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " meaasge = " + message);

    }
    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    // 의존관계 주입이 끝나면 호출해주겠다는 뜻

    @PostConstruct // 외부라이브러리에 적용안된다. 외부라이브러리를 초기화, 종료 해야하면 @Bean기능을 사용해야함
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
