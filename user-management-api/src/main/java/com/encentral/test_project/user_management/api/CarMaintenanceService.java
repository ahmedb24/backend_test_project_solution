
package com.encentral.test_project.user_management.api;

import com.encentral.test_project.commons.exceptions.ResourceNotFound;
import com.encentral.test_project.entities.Car;

/**
 *
 * @author James Akinniranye
 */
public interface CarMaintenanceService {
    
    Car find(String carId) throws ResourceNotFound;
    
    Car create(Car car) ;

    void delete(String carId) throws ResourceNotFound;

}
