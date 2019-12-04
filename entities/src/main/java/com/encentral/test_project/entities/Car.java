
package com.encentral.test_project.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "carMaintain")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT j FROM Car j")})
public class Car implements Serializable {
    
    

    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Size(min = 1, max = 64)
    @Column(name = "car_id", nullable = false, length = 64)
    private String carId;

    @Column(nullable = true)
    @Size(min = 1, max = 25)
    private String licensePlate;

    @Column(nullable = true)
    @Size(min = 1, max = 10)
    private String seatCount;

    @Column(nullable = true, name = "rating")
    @Size(min = 1, max = 10)
    private String rating;

    @Column(nullable = true, name = "convertable")
    private boolean convertable;

    @Column(nullable = true)
    @Size(min = 1, max = 25)
    private String engineType;
    
    
    @Column(nullable = true)
    @Size(min = 1, max = 25)
    private String carType;
    
    
    @Column(nullable = true, name = "attached")
    private boolean attached = false;
    
    public Car(){    
    }
    
    public Car(String carType){
        this.carType = carType;
    }
    
    public Car(String carId, String carType, String licensePlate, String seatCount, String rating, boolean convertable, String engineType){
        this.carId = carId;
        this.carType = carType;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.rating = rating;
        this.convertable = convertable;
        this.engineType = engineType;
    }
    
    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertable() {
        return convertable;
    }

    public void setConvertable(Boolean convertable) {
        this.convertable = convertable;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
    
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    
    public boolean getAttached() {
        return attached;
    }

    public void setAttached(boolean attached) {
        this.attached = attached;
    }
    
    
    

}
