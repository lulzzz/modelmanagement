package com.infosupport.machinelearning.modelmanagement.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An exception indicating that a model could not be found on disk
 */
@Getter
@AllArgsConstructor
public class ModelNotFoundException extends Exception {
    private final String name;
    private final int version;
}
