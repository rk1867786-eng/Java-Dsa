public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable()
        {@Override public void run() {try
            {
                    Thread.sleep(2000);
                    for (int i=0;i<1000000;i++)
                    {Math.sqrt(i);
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }}
        });
    }
}
