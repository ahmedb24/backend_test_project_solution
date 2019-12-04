/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import com.encentral.test_project.commons.exceptions.CarAlreadyInUse;
import com.encentral.test_project.commons.exceptions.ResourceNotFound;
import com.encentral.test_project.commons.models.DriverDTO;
import com.encentral.test_project.commons.models.DriverMapper;
import com.encentral.test_project.commons.util.MyObjectMapper;
import com.encentral.test_project.entities.JpaDriver;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.encentral.test_project.user_management.api.DriverService;
import com.encentral.test_project.commons.exceptions.NeedToBeOnline;
import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.*;
import static play.mvc.Results.badRequest;

/**
 *
 * @author poseidon
 */
@Api(value = "CarSelect")
@Transactional
public class CarSelectController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    DriverService driverService;

    @ApiOperation(value = "select a car", notes = "", httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = DriverDTO.class)
            }
    )
    public Result selectCar(String driverId, String carType) {
        
        try {
            return ok(Json.toJson(DriverMapper.jpaDriverToDriverDTO(driverService.selectCar(driverId, carType))));
        } catch (CarAlreadyInUse ex) {
            return notFound(ex.getMessage());
        }
     
    }

    
    @ApiOperation(value = "deselect a car", notes = "", httpMethod = "DELETE")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = DriverDTO.class)
            }
    )
    public Result deselectCar(String driverId, String carType) {
 
        try {
            return ok(Json.toJson(DriverMapper.jpaDriverToDriverDTO(driverService.deselectCar(driverId, carType))));
        } catch (ResourceNotFound ex) {
            return notFound(ex.getMessage());
        }
    
    }
}

