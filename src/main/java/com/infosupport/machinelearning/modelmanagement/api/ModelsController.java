package com.infosupport.machinelearning.modelmanagement.api;

import com.infosupport.machinelearning.modelmanagement.DocumentedEndpoint;
import com.infosupport.machinelearning.modelmanagement.storage.ModelData;
import com.infosupport.machinelearning.modelmanagement.storage.ModelStorageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@DocumentedEndpoint
@Api(tags = {"Models"})
public class ModelsController {
    private final ModelStorageService modelStorageService;

    @Autowired
    public ModelsController(ModelStorageService modelStorageService) {
        this.modelStorageService = modelStorageService;
    }

    @Value("${modelmanagement.storage.rootpath}")
    String modelRootDirectoryPath;

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @RequestMapping(
            value = "models/{name}",
            method = {RequestMethod.POST},
            consumes = "application/octet-stream",
            produces = "application/json")
    @ApiOperation(value = "uploadModel")
    @ApiResponses({
            @ApiResponse(code = 202, message = "The model is succesfully uploaded"),
            @ApiResponse(code = 400, message = "Invalid request data provided", response = GenericError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GenericError.class)
    })
    public ResponseEntity<?> uploadModel(
            @PathVariable String name,
            @ApiParam(value = "file", required = true) InputStream entity)
            throws IOException {
        modelStorageService.saveModel(name, entity);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(
            value = "models/{name}/{version}",
            consumes = "*/*",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_OCTET_STREAM_VALUE
            },
            method = RequestMethod.GET
    )
    @ApiOperation(value = "downloadModel")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The model data"),
            @ApiResponse(code = 404, message = "The model could not be found", response = GenericError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GenericError.class)
    })
    public ResponseEntity<?> downloadModel(@PathVariable("name") String name,
                                           @PathVariable("version") int version) throws Exception {
        ModelData responseData = modelStorageService.findModelByNameAndVersion(name, version);
        return ResponseEntity.ok(new InputStreamResource(responseData.getStream()));
    }
}

