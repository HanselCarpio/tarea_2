/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author hansel
 */
public class Vehicule {
    private String nameCar;
    private int annoCar;
    private float kmCar;
    private String countryCar;
    private int serieCar;
    private boolean active;

    public Vehicule() {
        this.nameCar = "";
        this.annoCar = 0;
        this.kmCar = 0;
        this.countryCar = "";
        this.serieCar = 0;
        this.active= true;
    }

    public Vehicule(String nameCar, int annoCar, float kmCar, String countryCar, int serieCar, boolean active) {
        this.nameCar = nameCar;
        this.annoCar = annoCar;
        this.kmCar = kmCar;
        this.countryCar = countryCar;
        this.serieCar = serieCar;
        this.active= active;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public int getAnnoCar() {
        return annoCar;
    }

    public void setAnnoCar(int annoCar) {
        this.annoCar = annoCar;
    }

    public float getKmCar() {
        return kmCar;
    }

    public void setKmCar(float kmCar) {
        this.kmCar = kmCar;
    }

    public String getCountryCar() {
        return countryCar;
    }

    public void setCountryCar(String countryCar) {
        this.countryCar = countryCar;
    }

    public int getSerieCar() {
        return serieCar;
    }

    public void setSerieCar(int serieCar) {
        this.serieCar = serieCar;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int getSize() {
        return getNameCar().length()*2 + 20 + 40 + 2 + 50 + 2;
    }
}
