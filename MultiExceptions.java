public class MultiExceptions {
    static void test() throws ArithmeticException, NumberFormatException{
        int a=10/0;
        int b=Integer.parseInt("ABC");
    }
    public static void main(String[] args){
        try{
            test();
        }catch (Exception e){
            System.out.println("Exception occurred :"+e);
        }
    }
}
