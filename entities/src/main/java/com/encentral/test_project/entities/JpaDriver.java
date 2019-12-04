/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encentral.test_project.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driver")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaDriver.findAll", query = "SELECT j FROM JpaDriver j")})
public class JpaDriver implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "driver_id", nullable = false, length = 64)
    private String driverId;

    @Column(nullable = false)
    @Size(min = 1, max = 25)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Column(nullable = false, name = "online_status")
    @NotNull(message = "online_status can not be null!")
    @Size(min = 1, max = 10)
    private String onlineStatus="online";

    @Column(nullable = false, name = "deleted")
    private boolean deleted;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModiied;
    
    
    @Column(nullable = false)
    @NotNull(message = "car type can not be null!")
    @Size(min = 1, max = 10)
    private String carType;
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="CARS")
    @NotNull(message = "cars can not be null!")
    private List<Car> cars = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="DRIVERS")
    private List<JpaDriver> drivers = new ArrayList<>();
    

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

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
    
    public void addCar(Car car) {
        System.out.println("addCar"+car);
        cars.add(car);
    }
    
    public Car getACar(List<Car> car) {
        return car.get(0);
    }
    
    public Car getCarFromDriver(String carType){
        if(cars.isEmpty()){
            return null;
        }
        for(Car car: cars){
            if(car.getCarType().equals(carType)){
                return car;
            }
        }
        return null;
    }
    
    public void deselectCar(Car car){
        cars.remove(car);
    }
    
    public void setDrivers(List<JpaDriver> driverss){
        if(drivers.isEmpty()){
            return;
        }
        
        for(JpaDriver driver: driverss){
            drivers.add(driver);
        }
        
    }
    
    public List<JpaDriver> getDrivers(){
        if(drivers.isEmpty()){
            return null;
        }
        return drivers;
    }
    
    

}
