package com.infosupport.machinelearning.modelmanagement.storage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Provides access to the model storage
 */
public interface ModelStorageService {
    /**
     * Stores a model on disk
     *
     * @param name        Name of the model to store
     * @param modelStream Stream containing the model data
     * @return Returns the metadata for the model
     */
    ModelMetadata saveModel(String name, InputStream modelStream) throws IOException;

    /**
     * Finds a model by its name and version
     *
     * @param name    Name of the model
     * @param version Version of the model
     * @return Returns the found model
     * @throws ModelNotFoundException Gets thrown when the model doesn't exist
     */
    ModelData findModelByNameAndVersion(String name, int version) throws ModelNotFoundException;
}
