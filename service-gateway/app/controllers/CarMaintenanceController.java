
package controllers;

import com.encentral.test_project.user_management.api.CarMaintenanceService;
import com.encentral.test_project.commons.exceptions.ResourceNotFound;
import com.encentral.test_project.commons.models.CarMaintenanceDTO;
import com.encentral.test_project.commons.models.CarMaintenanceMapper;
import com.encentral.test_project.commons.util.MyObjectMapper;
import com.encentral.test_project.entities.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


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
@Api(value = "CarMaintenance")
@Transactional
public class CarMaintenanceController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    CarMaintenanceService carMaintenanceService;

    @ApiOperation(value = "Get Car", notes = "", httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = CarMaintenanceDTO.class)
            }
    )
    public Result getCar(String carId) {
        try {
            return ok(Json.toJson(CarMaintenanceMapper.carToCarMaintenanceDTO(carMaintenanceService.find(carId))));
        } catch (ResourceNotFound ex) {
            return notFound(ex.getMessage());
        }
    }

    @ApiOperation(value = "Create a Car", notes = "", httpMethod = "POST")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done")
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "Application",
                        dataType = "com.encentral.test_project.commons.models.DriverDTO",
                        required = true,
                        paramType = "body",
                        value = "Application"
                )
            }
    )
    public Result createCar() {
        Form<CarMaintenanceDTO> bindFromRequest = formFactory.form(CarMaintenanceDTO.class).bindFromRequest();
        if (bindFromRequest.hasErrors()) {
            return badRequest(bindFromRequest.errorsAsJson());

        }
        Car create = carMaintenanceService.create(CarMaintenanceMapper.carMaintenanceDTotoCar(bindFromRequest.get()));

        return ok(Json.toJson(CarMaintenanceMapper.carToCarMaintenanceDTO(create)));
    }
    
    @ApiOperation(value = "Delete Car", notes = "", httpMethod = "DELETE")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done")
            }
    )
    public Result deleteCar(String carId) {
        try {
            carMaintenanceService.delete(carId);
            return noContent();
        } catch (ResourceNotFound ex) {
            return notFound(ex.getMessage());
        }
    }
}
