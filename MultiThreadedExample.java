public class MultiThreadedExample {
    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("TASK 1 completed");
            }
        });
        Thread t2 = new Thread(()-> {System.out.println("task 2 completed");});
        Thread t3 = new Thread(()-> {System.out.println("task 3 completed");});

        t1.start();
        //t2.sleep();
        t2.start();
        t3.start();

    }
}



//Thread t1 = new Thread(new runnable()
//
//public void run()
//{
//    System.out.println("task 1 completed ");
//
//});