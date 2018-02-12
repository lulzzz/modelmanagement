package com.infosupport.machinelearning.modelmanagement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a class with this annotation to include it in the API documentation.
 * Please note that you should only include API endpoints that you wish to expose to the public.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DocumentedEndpoint {
}
