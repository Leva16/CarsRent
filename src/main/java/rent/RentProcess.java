package rent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentProcess {

    private final int RENT_TIME = 3000;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private List<Car> carList;
    private List<Client> clientList;

    private Manager manager;

    public RentProcess(final List<Car> carList, final List<Client> clientList) {

        this.carList = carList;
        this.clientList = clientList;

        manager = new Manager(carList);
    }


    public void startRent() {

        for (Client client: clientList) {
            Thread thread = new Thread(() -> rentCarToClient(client));
            thread.start();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void rentCarToClient(final Client client) {

        boolean canIRent = askManagerForCar(client, manager);

        if (canIRent) {
            rentCarToClient(client);
        }
    }

    private boolean askManagerForCar(final Client client, final Manager manager) {

        Car car = null;

        synchronized (manager.getCoffee()) {

            List<String> availableCars = new ArrayList<>();
            List<String> totalCars = manager.getTotalCarNames();

            if (client.isDrive()) {

//                printWithTime("Client %s drove a car and went home.", client.getName());
                return false;
            }

            for (String carName: totalCars) {
                if (manager.isCarAvailable(carName)) {
                    availableCars.add(carName);
                }
            }

            printWithTime("Client %s has list of available cars: %s.", client.getName(), availableCars);

            if (availableCars.isEmpty()) {

                printWithTime("Client %s is waiting a car.", client.getName());

                try {
                    manager.getCoffee().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return askManagerForCar(client, manager);

            } else {

                car = manager.giveCarToClient(availableCars.get(0));

                printWithTime("Give the car \"%s\" to \"%s\".",
                        car.getName(), client.getName());
                manager.getCoffee().notifyAll();
            }

        }

        printWithTime("Client %s needs 10 sec to ride the car %s.", client.getName(), car.getName());

        try {
            Thread.sleep(RENT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.setDrive(true);

        printWithTime("Return car \"%s\" from \"%s\" to manager.",
                car.getName(), client.getName());

        manager.returnRentCar(car);

        return true;
    }

    private void printWithTime(final String text, Object... args) {

        String date = dateFormat.format(new Date());

        System.out.println(date + ": " + String.format(text, args));
    }
}
