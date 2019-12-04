/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encentral.test_project.commons.models;

import com.encentral.test_project.entities.Car;
import com.encentral.test_project.entities.JpaDriver;


public class CarMaintenanceMapper {

    public static CarMaintenanceDTO carToCarMaintenanceDTO(Car car) {
        CarMaintenanceDTO dTO = new CarMaintenanceDTO();
        dTO.setCarId(car.getCarId());
        dTO.setLicensePlate(car.getLicensePlate());
        dTO.setSeatCount(car.getSeatCount());
        dTO.setRating(car.getRating());
        dTO.setConvertable(car.getConvertable());
        dTO.setEngineType(car.getEngineType());
        dTO.setCarType(car.getCarType());
        dTO.setAttached(car.getAttached());
        return dTO;
    }

    public static Car carMaintenanceDTotoCar(CarMaintenanceDTO dTO) {
        Car car = new Car();
        car.setCarId(dTO.getCarId());
        car.setLicensePlate(dTO.getLicensePlate());
        car.setSeatCount(dTO.getSeatCount());
        car.setRating(dTO.getRating());
        car.setConvertable(dTO.getConvertable());
        car.setEngineType(dTO.getEngineType());
        car.setCarType(dTO.getCarType());
        car.setAttached(dTO.getAttached());
        return car;
    }
}
