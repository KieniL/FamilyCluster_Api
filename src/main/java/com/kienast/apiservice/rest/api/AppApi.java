/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.3.4).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.kienast.apiservice.rest.api;

import com.kienast.apiservice.rest.api.model.ApplicationModel;
import com.kienast.apiservice.rest.api.model.ApplicationResponseModel;
import com.kienast.apiservice.rest.api.model.ApplicationWithoutJwtModel;
import com.kienast.apiservice.rest.api.model.UpdateApplicationModel;
import com.kienast.apiservice.rest.api.model.UpdatedModel;
import com.kienast.apiservice.rest.api.model.VerifiedModel;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@Validated
@Api(value = "app", description = "the app API")
public interface AppApi {

    @ApiOperation(value = "add an application", nickname = "addApplication", notes = "", response = ApplicationModel.class, tags={ "app", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Schemas", response = ApplicationModel.class),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 400, message = "Bad Request") })
    @RequestMapping(value = "/app",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ApplicationModel> addApplication(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ApplicationModel applicationModel);


    @ApiOperation(value = "Add User to App", nickname = "addUser2App", notes = "", response = UpdatedModel.class, tags={ "app", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "App", response = UpdatedModel.class) })
    @RequestMapping(value = "/app/{appname}/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<UpdatedModel> addUser2App(@ApiParam(value = "the name the app",required=true) @PathVariable("appname") String appname,@ApiParam(value = "the name of the user",required=true) @PathVariable("username") String username);


    @ApiOperation(value = "Get an App", nickname = "getApp", notes = "", response = ApplicationWithoutJwtModel.class, tags={ "app", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "App", response = ApplicationWithoutJwtModel.class) })
    @RequestMapping(value = "/app/{appname}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ApplicationWithoutJwtModel> getApp(@ApiParam(value = "the name the app",required=true) @PathVariable("appname") String appname);


    @ApiOperation(value = "get all applications", nickname = "getApplications", notes = "", response = ApplicationResponseModel.class, responseContainer = "List", tags={ "app", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Schemas", response = ApplicationResponseModel.class, responseContainer = "List") })
    @RequestMapping(value = "/app",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ApplicationResponseModel>> getApplications();


    @ApiOperation(value = "update an application", nickname = "updateApplication", notes = "", response = ApplicationModel.class, tags={ "app", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Schemas", response = ApplicationModel.class),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 400, message = "Bad Request") })
    @RequestMapping(value = "/app",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ApplicationModel> updateApplication(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpdateApplicationModel updateApplicationModel);


    @ApiOperation(value = "Verify if user is allowed for app", nickname = "verifyUserForApp", notes = "", response = VerifiedModel.class, tags={ "app", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "App", response = VerifiedModel.class) })
    @RequestMapping(value = "/app/{appname}/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<VerifiedModel> verifyUserForApp(@ApiParam(value = "the name the app",required=true) @PathVariable("appname") String appname,@ApiParam(value = "the name of the user",required=true) @PathVariable("username") String username);

}
