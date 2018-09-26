package rent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Manager {

        private List<String> totalCarNames;

        private Map<String, Car> availableCars = new HashMap<>();

        private final Object coffe = new Object();

        public Manager(final List<Car> carList) {
            for (Car car: carList) {
                availableCars.put(car.getName(), car);
            }

            totalCarNames = getCarNames(carList);
        }

        public Object getCoffe() { return coffe; }

        private List<String> getCarNames(final List<Car> carList) {
            return carList.stream()
                    .map(Car::getName)
                    .collect(Collectors.toList());
        }

        public List<String> getTotalCarNames() { return totalCarNames; }

        public boolean isCarAvailable() { return availableCars.size() != 0; }

        public Car giveCarToClient(final String carName) {

            Car car = availableCars.get(carName);
            availableCars.remove(carName);

            return car;
        }

        public void returnRentCar(final Car car) {

            availableCars.put(car.getName(), car);
        }
}