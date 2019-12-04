/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encentral.test_project.user_management.impl;

import com.encentral.test_project.commons.exceptions.CarAlreadyInUse;
import com.encentral.test_project.commons.exceptions.NeedToBeOnline;
import com.encentral.test_project.commons.exceptions.ResourceNotFound;
import com.encentral.test_project.entities.JpaDriver;
import com.encentral.test_project.entities.Car;
import com.encentral.test_project.user_management.api.DriverService;
import java.util.Date;
import javax.inject.Inject;
import play.db.jpa.JPAApi;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author James Akinniranye
 */
public class DefaultDriverService implements DriverService {

    @Inject
    JPAApi jPAApi;
    
    @Override
    public JpaDriver find(String driverId) throws ResourceNotFound {
        JpaDriver driver = jPAApi.em().find(JpaDriver.class, driverId);
        if (driver == null) {
            throw new ResourceNotFound(String.format("Driver with id %s not found", driverId));
        }
        return driver;
    }

    @Override
    public JpaDriver create(JpaDriver driverDO){
        driverDO.setDriverId(java.util.UUID.randomUUID().toString());
        driverDO.setDateCreated(new Date());
        jPAApi.em().persist(driverDO);
        return driverDO;  
    }
    
    @Override
    public JpaDriver selectCar(String driverId, String carType) throws CarAlreadyInUse {
        
        JpaDriver driverDO = jPAApi.em().find(JpaDriver.class, driverId);

        if (driverDO == null) {
            throw new CarAlreadyInUse(String.format("Driver with id %s not found", driverId));
        }
        
        String onlineStatus = driverDO.getOnlineStatus();
        String str2 = "online";
       
        Car car =(Car)jPAApi.em().createQuery("SELECT u FROM Car u " + "WHERE u.carType LIKE ?1").setParameter(1,carType).getSingleResult(); 

        boolean attached = car.getAttached();
    
        if(!(onlineStatus.equals(str2))){
            throw new CarAlreadyInUse(String.format("car %s already in use or driver is offline", carType));
        }
        
        if(!(attached == true)){
            driverDO.addCar(car);
            car.setAttached(true);
            jPAApi.em().persist(driverDO);
    
        } else {
            throw new CarAlreadyInUse(String.format("car %s already in use or driver is offline", carType));
            
        }
        
        return driverDO;
    }
    
    
     @Override
    public JpaDriver deselectCar(String driverId, String carType) throws ResourceNotFound{

        JpaDriver driverDO = jPAApi.em().find(JpaDriver.class, driverId);
        if (driverDO == null) {
            throw new ResourceNotFound(String.format("Driver with id %s not found", driverId));
        }
    
        Car carToDeselect = driverDO.getCarFromDriver(carType);
        boolean attached = carToDeselect.getAttached(); 
        if(carToDeselect == null && attached == false){
            throw new ResourceNotFound(String.format("Car type %s not found", carType));
            
        }else {
            driverDO.deselectCar(carToDeselect);
            carToDeselect.setAttached(false);
        }
        
        return driverDO;
    }
    
    
    
    @Override
    public void delete(String driverId) throws ResourceNotFound {
        jPAApi.em().remove(find(driverId));
    }
    
    @Override
    public List<JpaDriver> findDriver(String driverId, String carType, String username, String onlineStatus) throws ResourceNotFound { 
        
       
        //retrieve all drivers with matched criteria
        TypedQuery<JpaDriver> query = jPAApi.em().createQuery("SELECT j FROM JpaDriver j " + "WHERE j.driverId LIKE ?1", JpaDriver.class).setParameter(1, driverId);
        
        TypedQuery<JpaDriver> query2 = jPAApi.em().createQuery("SELECT j FROM JpaDriver j " + "WHERE j.carType LIKE ?2", JpaDriver.class).setParameter(2, carType);
        
        TypedQuery<JpaDriver> query3 = jPAApi.em().createQuery("SELECT j FROM JpaDriver j " + "WHERE j.username LIKE ?3", JpaDriver.class).setParameter(3, username);
        
        TypedQuery<JpaDriver> query4 = jPAApi.em().createQuery("SELECT j FROM JpaDriver j " + "WHERE j.onlineStatus LIKE ?4", JpaDriver.class).setParameter(4, onlineStatus);
   
        //create a drivers list with first query
        List<JpaDriver> drivers = query.getResultList();
        
        drivers.addAll(query2.getResultList());
        drivers.addAll(query3.getResultList());
        drivers.addAll(query4.getResultList());
        
        if (drivers == null) {
            throw new ResourceNotFound(String.format("Driver with any of the values %s, %s, %s, %s not found", driverId, carType, username, onlineStatus));
        }
  
        return drivers;
    }
    
    

}
