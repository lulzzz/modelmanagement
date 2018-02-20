package com.infosupport.machinelearning.modelmanagement.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Provides access to model data
 */
public interface ModelDataRepository {
    /**
     * Finds model data by name and version
     *
     * @param name    Name of the model
     * @param version Version of the model
     * @return Returns the model data stream
     */
    InputStream findByNameAndVersion(String name, int version) throws FileNotFoundException;

    /**
     * Stores model data in the model repository on disk
     *
     * @param name        Name of the model
     * @param version     Version of the model
     * @param modelStream Stream to store on disk
     */
    void save(String name, int version, InputStream modelStream) throws IOException;

    /**
     * Deletes an existing model
     *
     * @param name    Name of the model
     * @param version Version of the model
     * @throws IOException Gets thrown when the model folder can't be deleted
     */
    void delete(String name, int version) throws IOException;
}
