package ThinkInJava.c21.c2;

public class ThreadVariations {
    public static void main(String[] args) {
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}

class InnerThread1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner extends Thread {
        public Inner(String name) {
            super(name);
            start();
        }

        @Override
        public String toString() {
            return "#" + getName() + "(" + countDown + ")";
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) {
                        return;
                    }
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}

class InnerThread2 {
    private int countDown = 5;
    private Thread t;

    public InnerThread2(String name) {
        t = new Thread(name) {
            @Override
            public String toString() {
                return "#" + getName() + "(" + countDown + ")";
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}

class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }

    private class Inner implements Runnable {
        Thread thread;

        Inner(String name) {
            thread = new Thread(this, name);
            thread.start();
        }

        @Override
        public String toString() {
            return "#" + thread.getName() + "(" + countDown + ")";
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) {
                        return;
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class InnerRunnable2 {
    private int countDown = 5;
    private Thread thread;

    public InnerRunnable2(String name) {
        thread = new Thread(new Runnable() {
            @Override
            public String toString() {
                return "#" + Thread.currentThread().getName() + "(" + countDown + ")";
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, name);
    }
}

class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                @Override
                public String toString() {
                    return "#" + getName() + "(" + countDown + ")";
                }

                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            if (--countDown == 0) {
                                return;
                            }
                            sleep(1100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
    }
}
