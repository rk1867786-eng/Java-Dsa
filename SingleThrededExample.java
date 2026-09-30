public class SingleThrededExample {
    public static void main(String[] args){
        task1();//completes first
        task2();//
        task3();//
    }
    static void task1(){
        System.out.println("task  1 completed");
    }
    static void task2(){
        System.out.println("task 2 completed");
    }
    static void task3(){
        System.out.println("task 3 completed");
    }
}
