package com.infosupport.machinelearning.modelmanagement.services;

import com.infosupport.machinelearning.modelmanagement.models.Model;
import com.infosupport.machinelearning.modelmanagement.repositories.ModelRepository;

import java.io.File;

public class ModelFileService {

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
}
