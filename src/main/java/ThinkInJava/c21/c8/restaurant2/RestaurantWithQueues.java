//package ThinkInJava.c21.c8.restaurant2;
//
//import ThinkInJava.c19.c7.Course;
//import ThinkInJava.c19.c7.Food;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.*;
//
//import static ThinkInJava.util.Utils.print;
//
///**
// * Created by zhao_yukuan@163.com
// * on 2017/7/21
// */
//class Order {
//    private static int counter = 0;
//    private final int id = counter++;
//    private final Customer customer;
//    private final WaitPerson waitPerson;
//    private final Food food;
//
//    public Order(Customer customer, WaitPerson waitPerson, Food food) {
//        this.customer = customer;
//        this.waitPerson = waitPerson;
//        this.food = food;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public WaitPerson getWaitPerson() {
//        return waitPerson;
//    }
//
//    public Food getFood() {
//        return food;
//    }
//
//    public String toString() {
//        return "Order: " + id + " food: " + food +
//                " for: " + customer +
//                " served by: " + waitPerson;
//    }
//}
//
//class Plate {
//    private final Order order;
//    private final Food food;
//
//    public Plate(Order order, Food food) {
//        this.order = order;
//        this.food = food;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public Food getFood() {
//        return food;
//    }
//
//    public String toString() {
//        return food.toString();
//    }
//}
//
//class Customer implements Runnable {
//    private static int counter = 0;
//    private final int id = counter++;
//    private final WaitPerson waitPerson;
//    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();
//
//    public Customer(WaitPerson waitPerson) {
//        this.waitPerson = waitPerson;
//    }
//
//    public void deliver(Plate p) throws InterruptedException {
//        placeSetting.put(p);
//    }
//
//    public void run() {
//        for (Course course : Course.values()) {
//            // 点菜
//            Food food = course.randomSelection();
//            try {
//                //服务员把菜给饭店
//                waitPerson.placeOrder(this, food);
//                //等着吃
//                print(this + "eating " + placeSetting.take().getFood());
//            } catch (InterruptedException e) {
//                print(this + "waiting for " + course + " interrupted");
//                break;
//            }
//        }
//        print(this + "finished meal, leaving");
//    }
//
//    public String toString() {
//        return "Customer " + id + " ";
//    }
//}
//
//class WaitPerson implements Runnable {
//    private static int counter = 0;
//    private final int id = counter++;
//    private final Restaurant restaurant;
//    private BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();
//
//    public WaitPerson(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }
//
//    public void placeOrder(Customer customer, Food food) {
//        try {
//            restaurant.receiveOrder(new Order(customer, this, food));
//        } catch (InterruptedException e) {
//            print(this + " placeOrder interrupted");
//        }
//    }
//
//    public void putPlate(Plate plate) throws InterruptedException {
//        filledOrders.put(plate);
//    }
//
//    public void run() {
//        try {
//            while (!Thread.interrupted()) {
//                // 等着从厨师那里取盘子
//                Plate plate = filledOrders.take();
//                print(this + "received " + plate + " delivering to " + plate.getOrder().getCustomer());
//                //把盘子给顾客
//                plate.getOrder().getCustomer().deliver(plate);
//            }
//        } catch (InterruptedException e) {
//            print(this + " interrupted");
//        }
//        print(this + " off duty");
//    }
//
//    public String toString() {
//        return "WaitPerson " + id + " ";
//    }
//}
//
//class Chef implements Runnable {
//    private static int counter = 0;
//    private final int id = counter++;
//    private final Restaurant restaurant;
//    private static Random rand = new Random();
//
//    public Chef(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }
//
//    public void run() {
//        try {
//            while (!Thread.interrupted()) {
//                //从饭店要菜单
//                Order order = restaurant.sendOrder();
//                Food requestedItem = order.getFood();
//                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
//                //把菜装盘子里
//                Plate plate = new Plate(order, requestedItem);
//                //服务员装盘
//                order.getWaitPerson().putPlate(plate);
//            }
//        } catch (InterruptedException e) {
//            print(this + " interrupted");
//        }
//        print(this + " off duty");
//    }
//
//    public String toString() {
//        return "Chef " + id + " ";
//    }
//}
//
//class Restaurant implements Runnable {
//    private List<WaitPerson> waitPersons = new ArrayList<>();
//    private ExecutorService exec;
//    private static Random rand = new Random();
//    private BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
//
//    public Restaurant(ExecutorService e, int nWaitPersons, int nChefs) {
//        exec = e;
//        for (int i = 0; i < nWaitPersons; i++) {
//            WaitPerson waitPerson = new WaitPerson(this);
//            waitPersons.add(waitPerson);
//            exec.execute(waitPerson);
//        }
//        for (int i = 0; i < nChefs; i++) {
//            Chef chef = new Chef(this);
//            exec.execute(chef);
//        }
//    }
//
//    //Restaurant 接收 Order
//    public void receiveOrder(Order order) throws InterruptedException {
//        orders.put(order);
//    }
//
//    //Restaurant 发送 Order 给 chef
//    public Order sendOrder() throws InterruptedException {
//        return orders.take();
//    }
//
//    public void run() {
//        try {
//            while (!Thread.interrupted()) {
//                WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
//                Customer c = new Customer(wp);
//                exec.execute(c);
//                TimeUnit.MILLISECONDS.sleep(100);
//            }
//        } catch (InterruptedException e) {
//            print("Restaurant interrupted");
//        }
//        print("Restaurant closing");
//    }
//}
//
//public class RestaurantWithQueues {
//    public static void main(String[] args) throws Exception {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        Restaurant restaurant = new Restaurant(exec, 5, 2);
//        exec.execute(restaurant);
//        System.in.read();
//        exec.shutdownNow();
//    }
//}