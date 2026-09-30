public class THREAD {
    public static void main(String[] args){
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" FIRST ");
            }
        });
        Thread t2 = new Thread(()-> {System.out.println(" SECOND");});
        Thread t3 = new Thread(()-> {System.out.println(" SECOND");});

        t1.start();
//        t2.sleep(5000);
//        t2.wait(5000);
//        t2.interrupt(5000);
        t2.start();
        t3.start();
    }
}
