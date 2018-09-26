import rent.Car;
import rent.Client;
import rent.RentProcess;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Car car1 = new Car("Honda");
        Car car2 = new Car("Mazda");
        Car car3 = new Car("Skoda");

        List<Car> carList = Arrays.asList(car1, car2, car3);

        Client c1 = new Client("Ren");
        Client c2 = new Client("Andrew");
        Client c3 = new Client("Lenka");
        Client c4 = new Client("Bugor");
        Client c5 = new Client("Vanek");
        Client c6 = new Client("Arnold");
        Client c7 = new Client("Silvestr");
        Client c8 = new Client("Tom");
        Client c9 = new Client("Vikusya");
        Client c10 = new Client("John");
        Client c11 = new Client("Ted");

        List<Client> clientList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);


        RentProcess rentProcess = new RentProcess(carList, clientList);

        rentProcess.startRent();

    }
}
