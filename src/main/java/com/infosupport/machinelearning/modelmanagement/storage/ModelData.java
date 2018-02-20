package com.infosupport.machinelearning.modelmanagement.storage;

import lombok.Data;

import java.io.InputStream;

/**
 * Data Transfer Class for model details
 */
@Data
public class ModelData {
    private final ModelMetadata metadata;
    private final InputStream stream;
}
