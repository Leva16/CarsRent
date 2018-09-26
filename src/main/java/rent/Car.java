package rent;

public class Car {

    private String modelName;
    private boolean isCarAvailable = true;

    public Car (final String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public boolean isCarAvailable() {
        return isCarAvailable;
    }

    public void setCarAvailable(boolean carAvailable) {
        isCarAvailable = carAvailable;
    }
  
    @Override
    public String toString() {
        return modelName;
    }
}
