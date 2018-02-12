package com.infosupport.machinelearning.modelmanagement.storage;

import com.infosupport.machinelearning.modelmanagement.controllers.ModelsController;
import com.infosupport.machinelearning.modelmanagement.repositories.ModelRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModelControllerTest {

    @InjectMocks
    private ModelsController mc;

    @Mock
    private ModelRepository modelRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUploadModel() {

    }

//    @Test
//    public void testModelGet() {
//
//    }
}
