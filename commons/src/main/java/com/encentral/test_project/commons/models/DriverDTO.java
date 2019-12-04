package com.encentral.test_project.commons.models;

import com.encentral.test_project.entities.Car;
import com.encentral.test_project.entities.JpaDriver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO {

    private String driverId;

    @NotNull(message = "Username can not be null!")
    @Size(min = 5)
    private String username;

    @NotNull(message = "Password can not be null!")
    @Size(min = 5)
    @JsonIgnore
    private String password;

    @NotNull(message = "online_status can not be null!")
    private String onlineStatus;
    
    @NotNull(message = "car type can not be null!")
    private String carType;

    private Date dateCreated;
    private Date dateModiied;
    
    @NotNull(message = "car list can not be null!")
    private List<Car> cars = new ArrayList<>();
    
    List<JpaDriver> drivers = new ArrayList<>();

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModiied() {
        return dateModiied;
    }

    public void setDateModiied(Date dateModiied) {
        this.dateModiied = dateModiied;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
    
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    
    public List<Car> getCars() {
        return cars;
    }

    public void addCars(List<Car> carss) {
        
        for(int i=0; i<carss.size(); i++){
            cars.add(carss.get(i));
        }
        
    }
    
    public void setDrivers(List<JpaDriver> driverss){
        if(driverss.isEmpty()){
            return;
        }
        for(int i=0; i<driverss.size(); i++){
            drivers.add(driverss.get(i));
        }
      
    }
    
    public List<JpaDriver> getDrivers(){
        return drivers;
    }
    
}
