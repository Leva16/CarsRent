package rent;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {

        private Map<String, Car> availableCars = new HashMap<>();

        private final Object coffee = new Object();

        public Manager(final List<Car> carList) {
            for (Car car: carList) {
                availableCars.put(car.getName(), car);
            }
        }

        public List<Car> getAvailableCars() {
            return new ArrayList<>(availableCars.values());
        }

        public Object getCoffee() { return coffee; }

        public Car giveCarToClient(final String carName) {

            Car car = availableCars.get(carName);
            availableCars.remove(carName);

            return car;
        }

        public void returnRentCar(final Car car) {

            availableCars.put(car.getName(), car);
        }
}
