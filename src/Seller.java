public class Seller {

    private final static int SEARCH_CAR = 1000;
    private final static int CAR_ACCEPTANCE = 2000;

    private CarDealership carDealership;

    public Seller(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            Thread.sleep(SEARCH_CAR);
            while (carDealership.getCars().size() == 0) {
                System.out.println("Автомобилей нет в наличии");
                wait();
            }
            Thread.sleep(SEARCH_CAR);
            System.out.println(Thread.currentThread().getName() + " уехал на новом автомобиле");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carDealership.getCars().remove(0);
    }

    public void receiveCar() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(CAR_ACCEPTANCE);
                synchronized (this) {
                    carDealership.getCars().add(new Car());
                    System.out.println("Производитель Toyota выпустил 1 авто");
                    notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}