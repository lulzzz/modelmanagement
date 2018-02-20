package com.infosupport.machinelearning.modelmanagement.storage;

import com.infosupport.machinelearning.modelmanagement.models.Model;
import com.infosupport.machinelearning.modelmanagement.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
@AllArgsConstructor
public class ModelStorageServiceImpl implements ModelStorageService {
    private final ModelMetadataRepository modelMetadataRepository;
    private final ModelDataRepository modelDataRepository;

    public int getCurrentModelVersion(ModelRepository modelRepository, String name) {
        // Gets latest version from model repository and adds 1.
        Model currentModel = modelRepository.findTopByNameOrderByVersionDesc(name);
        int newVersionNumber = 1;
        if (currentModel != null) {
            newVersionNumber = currentModel.getVersion() + 1;
        }
        return newVersionNumber;
    }

    public String createDirectories(String modelRootDirectoryPath, String name) {
        // Create root directory
        File modelRootDirectory = new File(modelRootDirectoryPath);
        if (!modelRootDirectory.exists()) {
            modelRootDirectory.mkdirs();
        }
        // Create folder for model
        String modelDirPath = modelRootDirectoryPath + File.separator + name;
        File modelDirectory = new File(modelDirPath);
        if (!modelDirectory.exists()) {
            modelDirectory.mkdir();
        }
        return modelDirPath;
    }

    /**
     * Saves a model and its metadata
     *
     * @param name        Name of the model to store
     * @param modelStream Stream containing the model data
     * @return The saved model metadata
     */
    @Override
    public ModelMetadata saveModel(String name, InputStream modelStream) throws IOException {
        ModelMetadata latestVersion = modelMetadataRepository.findTopByNameOrderByVersionDesc(name);
        int version = 1;

        if (latestVersion != null) {
            version = latestVersion.getVersion() + 1;
        }

        ModelMetadata storedMetadata = modelMetadataRepository.save(new ModelMetadata(name, version, new Date()));
        modelDataRepository.save(name, version, modelStream);

        return storedMetadata;
    }

    /**
     * Finds a model by its name and version
     *
     * @param name    Name of the model
     * @param version Version of the model
     * @return Returns the found model
     * @throws ModelNotFoundException Gets thrown when the model could not be found
     */
    @Override
    public ModelData findModelByNameAndVersion(String name, int version) throws ModelNotFoundException {
        ModelMetadata metadata = modelMetadataRepository.findByNameAndVersion(name, version);

        if (metadata == null) {
            throw new ModelNotFoundException(name, version);
        }

        try {
            InputStream modelStream = modelDataRepository.findByNameAndVersion(name, version);

            return new ModelData(metadata, modelStream);
        } catch (FileNotFoundException ex) {
            throw new ModelNotFoundException(name, version);
        }
    }
}
