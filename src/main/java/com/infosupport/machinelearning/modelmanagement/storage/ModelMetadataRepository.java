package com.infosupport.machinelearning.modelmanagement.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedQuery;
import java.util.List;

/**
 * Provides access to model metadata in the database
 */
public interface ModelMetadataRepository extends CrudRepository<ModelMetadata, Long> {

    /**
     * Finds the models sorted by version, descending
     *
     * @param name The name of the model to find
     * @return Returns a list of models, sorted by the version in descending order
     */
    ModelMetadata findTopByNameOrderByVersionDesc(String name);

    /**
     * Finds a single version of a model
     *
     * @param name    Name of the model
     * @param version Version of the model
     * @return Returns the metadata for the model
     */
    ModelMetadata findByNameAndVersion(String name, int version);
}
