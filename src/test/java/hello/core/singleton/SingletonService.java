package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 1. static 영역에 객체를 딱 하나만 생성해둔다.
    // 자기 자신을 내부에 private static 으로 가지고있다.
    // 이렇게하면 클래스 레벨에 올라가기때문에 딱 하나만 존재하게 된다.

    // 2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    // 오직 getInstance() 메서드를 통해서만 조회할 수 있다. 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
    public static SingletonService getInstance() {
        return instance;
    }
    // 생성자를 private 로 생성하여 new 연산자로 생성을 방지하고
    // 객체변수인 instance를 통해 메모리에 한번 할당시킨다.
    // 싱글톤방식은 여러번 생성해도 getInstance() 메소드에 의해 한번만 할당된 객체의 주소값을 참조한다
    // 일반 new연산자 방식은 객체를 생성할 때마다 메모리에 새로 만들기 때문에 모두 다른 메모리값으로 처리된다.

    // 3. 딱 한개의 객체 인스턴스로만 존재해야 하므로
    // 생성자를 private로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
