package model;

public class Vehicle {

    private int vehicleId;
    private String vehicleName;
    private String brand;
    private String vehicleType;
    private double rentPerDay;
    private boolean available;

    public Vehicle() {
    }

    public Vehicle(String vehicleName, String brand, String vehicleType,
            double rentPerDay, boolean available) {

        this.vehicleName = vehicleName;
        this.brand = brand;
        this.vehicleType = vehicleType;
        this.rentPerDay = rentPerDay;
        this.available = available;
    }
    public Vehicle(int vehicleId, String vehicleName, String brand,
            String vehicleType, double rentPerDay, boolean available) {

        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.vehicleType = vehicleType;
        this.rentPerDay = rentPerDay;
        this.available = available;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(double rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
