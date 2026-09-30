public class Student {
    static int roll =16;

    Integer a1=78;
    Integer a2=78;
    void display1(){
        System.out.println(a1.equals(a2));
    }
}
class Test{
    public static void main(String[] args){
        System.out.println(Student.roll);
         Student s1=new Student();
         s1.display1();
        System.out.println("NO RESPONSE");
    }
}