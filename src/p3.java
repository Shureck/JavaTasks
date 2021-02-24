import java.util.*;
import java.util.concurrent.Semaphore;

interface p3_HashMap {
    HashMap<Integer, String> hash = new HashMap<>();
    public void add(int i);
    public String get(int i);
}

interface p3_List {
    static final Semaphore semaphore = new Semaphore(1);
    List list = new ArrayList();
    public void add_list(int i) throws InterruptedException;
    public int get_list(int i) throws InterruptedException;
}

public class p3 implements p3_HashMap, p3_List{

    @Override
    synchronized public void add(int i) {
        hash.put(i,String.valueOf(i));
    }

    @Override
    synchronized public String get(int i) {
        return hash.get(i);
    }

    @Override
    public void add_list(int i) throws InterruptedException {
        semaphore.acquire();
        list.add(i);
        semaphore.release();

    }

    @Override
    public int get_list(int i) throws InterruptedException {
        semaphore.acquire();
        int num = (int) list.get(i);
        semaphore.release();
        return num;
    }

    static class Test implements  p3_HashMap, p3_List {

        @Override
        synchronized public void add(int i) {
            hash.put(i, String.valueOf(i));
        }

        @Override
        synchronized public String get(int i) {
            return hash.get(i);
        }

        @Override
        public void add_list(int i) throws InterruptedException {
            semaphore.acquire();
            list.add(i);
            semaphore.release();

        }

        @Override
        public int get_list(int i) throws InterruptedException {
            semaphore.acquire();
            int num = (int) list.get(i);
            semaphore.release();
            return num;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Test test = new Test();

        Thread one = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                test.add(i);
                try {
                    test.add_list(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread two = new Thread(()->{
            for (int i = 10; i < 15; i++) {
                test.add(i);
                try {
                    test.add_list(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        one.start();
        two.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<10;i++){
            System.out.println(test.get(i));
        }
        System.out.println();
        for(int i=0;i<10;i++){
            System.out.println(test.get_list(i));
        }
}
}
