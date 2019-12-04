package com.encentral.test_project.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.encentral.test_project.entities.JpaDriver;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarMaintenanceDTO {

    private String carId;
    

    @NotNull(message = "license plate can not be null!")
    @Size(min = 5)
    private String licensePlate;

    @NotNull(message = "seat count can not be null!")
    @Size(min = 5)
    private String seatCount;

    @NotNull(message = "rating can not be null!")
    @Size(min = 5)
    private String rating;
    
    @NotNull(message = "engine type can not be null!")
    @Size(min = 5)
    private String engineType;
    
    
    private boolean convertable;
    
    
    @Size(min = 1, max = 25)
    @NotNull(message = "car type can not be null!")
    private String carType;
    
    
    private boolean attached;
    

   
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
