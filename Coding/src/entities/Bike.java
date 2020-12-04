package entities;

import dataaccesslayer.BikeDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Bike {
    private boolean isInUse;
    private int barcode;
    private String type;
    private int value;
    private int priceForFirst30Minutes;
    private int priceFor15MinutesAfter30Minutes;
    private int remainBattery;
    private float maxTime;
    private String licensePlate;

    public Bike(boolean isInUse, int barcode, String type, int value, int priceForFirst30Minutes, int priceFor15MinutesAfter30Minutes, int remainBattery, float maxTime, String licensePlate) {
        this.isInUse = isInUse;
        this.barcode = barcode;
        this.type = type;
        this.value = value;
        this.priceForFirst30Minutes = priceForFirst30Minutes;
        this.priceFor15MinutesAfter30Minutes = priceFor15MinutesAfter30Minutes;
        this.remainBattery = remainBattery;
        this.maxTime = maxTime;
        this.licensePlate = licensePlate;
    }

    public Bike(int barcode, String type) {
        this.barcode = barcode;
        this.type = type;
        value = 10000;
        priceFor15MinutesAfter30Minutes = 300000;
        priceForFirst30Minutes = 100000;
        remainBattery = 90;
        maxTime = 5.5f;
        licensePlate = "30A.19954";
    }

    public static ArrayList<Bike> getRandomBicycles(){
        ArrayList<Bike> bikes = new ArrayList<>();
        Random random = new Random();
        int n = random.nextInt(30) + 1;
        ArrayList<Integer> s = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            s.add(i);
        Collections.shuffle(s);
        for(int i: s){
            bikes.add(new Bike(i, "Lamboghini"));
        }
        return bikes;
    }

    @Override
    public String toString() {
        return Integer.toString(barcode) + " - " + type;
    }


    public boolean isInUse() {
        return isInUse;
    }

    public int getBarcode() {
        return barcode;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getPriceForFirst30Minutes() {
        return priceForFirst30Minutes;
    }

    public int getPriceFor15MinutesAfter30Minutes() {
        return priceFor15MinutesAfter30Minutes;
    }

    public int getRemainBattery() {
        return remainBattery;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPriceForFirst30Minutes(int priceForFirst30Minutes) {
        this.priceForFirst30Minutes = priceForFirst30Minutes;
    }

    public void setPriceFor15MinutesAfter30Minutes(int priceFor15MinutesAfter30Minutes) {
        this.priceFor15MinutesAfter30Minutes = priceFor15MinutesAfter30Minutes;
    }

    public void setRemainBattery(int remainBattery) {
        this.remainBattery = remainBattery;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void update(boolean isInUse){
        this.isInUse = isInUse;
    }

    public void saveToDatabase(){
        BikeDAO.save(this.isInUse, barcode, type, value, priceForFirst30Minutes, priceFor15MinutesAfter30Minutes, remainBattery, maxTime, licensePlate);
    }

    // undone
    public String getGeneralInfo(){
        return "";
    }

    public String getDetailInfo(){
        return Integer.toString(barcode) + " - " + type + " - " + Integer.toString(value) + " - " +
                Integer.toString(priceForFirst30Minutes) + " - " + Integer.toString(priceFor15MinutesAfter30Minutes) + " - " +
                Integer.toString(remainBattery) + " - " + Float.toString(maxTime) + " - " + licensePlate;
    }
}