/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encentral.test_project.user_management.impl;

import com.encentral.test_project.commons.exceptions.CarAlreadyInUse;
import com.encentral.test_project.commons.exceptions.NeedToBeOnline;
import com.encentral.test_project.commons.exceptions.ResourceNotFound;
import com.encentral.test_project.entities.Car;
import com.encentral.test_project.user_management.api.CarMaintenanceService;
import java.util.Date;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

/**
 *
 * @author James Akinniranye
 */
public class DefaultCarMaintenanceService implements CarMaintenanceService {

    @Inject
    JPAApi jPAApi;

    @Override
    public Car find(String carId) throws ResourceNotFound {
        Car car = jPAApi.em().find(Car.class, carId);
        if (car == null) {
            throw new ResourceNotFound(String.format("Car with id %s not found", carId));
        }
        return car;
    }

    @Override
    public Car create(Car car) {
        car.setCarId(java.util.UUID.randomUUID().toString());
        car.setDateCreated(new Date());
        jPAApi.em().persist(car);
        return car;
    }

    @Override
    public void delete(String carId) throws ResourceNotFound {
        jPAApi.em().remove(find(carId));
    }

}
