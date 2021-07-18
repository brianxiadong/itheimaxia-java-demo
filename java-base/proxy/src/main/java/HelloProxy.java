public class HelloProxy implements HelloInterface{
    private HelloInterface helloInterface = new Hello();
    @Override
    public void sayHello() {
        System.out.println("Before invoke sayHello" );
        helloInterface.sayHello();
        System.out.println("After invoke sayHello");
    }

    public static void main(String[] args) {
        HelloInterface helloProxy = new HelloProxy();
        helloProxy.sayHello();
    }
}