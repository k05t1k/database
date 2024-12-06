package com.example.dbapp;

public class Cars {
    private int ID_Car;
    private String Car_Name;
    private String Car_Mark;

    public Cars(int ID_Car, String car_Name, String car_Mark) {
        this.ID_Car = ID_Car;
        this.Car_Name = car_Name;
        this.Car_Mark = car_Mark;
    }

    public int getID_Car() {
        return ID_Car;
    }
    public void setID_Car(int ID_Car) {
        this.ID_Car = ID_Car;
    }

    public String getCar_Name() {
        return Car_Name;
    }
    public void setCar_Name(String car_Name) {
        Car_Name = car_Name;
    }

    public String getCar_Mark() {
        return Car_Mark;
    }

    public void setCar_Mark(String car_Mark) {
        Car_Mark = car_Mark;
    }
}
