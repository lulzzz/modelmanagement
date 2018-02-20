package com.infosupport.machinelearning.modelmanagement.controllers;

import com.infosupport.machinelearning.modelmanagement.DocumentedEndpoint;
import com.infosupport.machinelearning.modelmanagement.models.GenericError;
import com.infosupport.machinelearning.modelmanagement.models.Model;
import com.infosupport.machinelearning.modelmanagement.repositories.ModelRepository;
import com.infosupport.machinelearning.modelmanagement.services.ModelFileService;
import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
@DocumentedEndpoint
@Api(tags={ "Models" })
public class ModelsController {

    @Autowired
    private ModelRepository modelRepository;

    @Value("${modelmanagement.model.root.directory}")
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
    public ResponseEntity<?> uploadModel(@PathVariable String name, @ApiParam(value = "file", required = true) InputStream entity) {
        // TODO: Test if name is valid "The name should be a slug that contains only alphanumeric characters and dashes."
        try {
            //Determine filename, filepath and version number.
            ModelFileService modelFileService = new ModelFileService();
            int newVersionNumber = modelFileService.getCurrentModelVersion(modelRepository, name);
            String modelDirPath = modelFileService.createDirectories(modelRootDirectoryPath, name);
            String fileName = String.format("%s_%d.zip", name, newVersionNumber);
            String filePath = modelDirPath + File.separator + fileName;

            // Write file to file directory.
            FileUtils.copyInputStreamToFile(entity, new File(filePath));
            // Add reference to model to database
            Model newModel = new Model(name, filePath, newVersionNumber, new Date());
            modelRepository.save(newModel);

        } catch (IOException e) {
            //TODO: Log to logging facility
            return ResponseEntity.status(500).body(new GenericError("Failed to process the request."));
        }

        return ResponseEntity.accepted().build();
    }
}

