package ThinkInJava.c21.c3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/8
 */
public class ExplicitCriticalSection {
    public static void main(String[] args) {
        PairManagerReentrantLock
                pman1 = new ExplicitPairManager1(),
                pman2 = new ExplicitPairManager2();
        ExplicitCriticalSection.testApproaches(pman1, pman2);
    }

    private static void testApproaches(PairManagerReentrantLock pman1, PairManagerReentrantLock pman2) {
        ExecutorService executor = Executors.newCachedThreadPool();
        PairManipulatorReentrantLock
                pm1 = new PairManipulatorReentrantLock(pman1),
                pm2 = new PairManipulatorReentrantLock(pman2);
        PairCheckerReentrantLock
                pcheck1 = new PairCheckerReentrantLock(pman1),
                pcheck2 = new PairCheckerReentrantLock(pman2);
        executor.execute(pm1);
        executor.execute(pm2);
        executor.execute(pcheck1);
        executor.execute(pcheck2);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        System.out.println("pm1:" + pm1 + "\npm2:" + pm2);
        System.exit(0);
    }
}

abstract class PairManagerReentrantLock {
    AtomicInteger checkCounter = new AtomicInteger();
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());
    protected ReentrantLock lock = new ReentrantLock();

    public Pair getPair() {
        lock.lock();
        try {
            return new Pair(p.getX(), p.getY());
        } finally {
            lock.unlock();
        }
    }

    protected void store(Pair p) {
        storage.add(p);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void increment();
}

class ExplicitPairManager1 extends PairManagerReentrantLock {
    @Override
    public void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

class ExplicitPairManager2 extends PairManagerReentrantLock {
    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }
}

class PairManipulatorReentrantLock implements Runnable {
    private PairManagerReentrantLock pm;

    public PairManipulatorReentrantLock(PairManagerReentrantLock pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    @Override
    public String toString() {
        return "Pair:" + pm.getPair() + "checkCounter=" + pm.checkCounter.get();
    }
}

class PairCheckerReentrantLock implements Runnable {
    private PairManagerReentrantLock pm;

    public PairCheckerReentrantLock(PairManagerReentrantLock pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}
