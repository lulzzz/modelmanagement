package com.infosupport.machinelearning.modelmanagement.storage;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ModelDataRepositoryTests {
    private String modelStoragePath;
    private ModelDataRepository modelDataRepository;

    @Before
    public void setUp() throws Exception {
        modelStoragePath = "test_models";
        modelDataRepository = new ModelDataRepositoryImpl(modelStoragePath);
    }

    @After
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(new File(modelStoragePath));
    }

    @Test
    public void saveStoresModelOnDisk() throws Exception {
        modelDataRepository.save("test-model",1, new ByteArrayInputStream("hello-world".getBytes()));

        File expectedModelFile = Paths.get(modelStoragePath, "test-model", "1", "model.zip").toFile();

        assertThat(expectedModelFile.exists(), equalTo(true));
    }

    @Test
    public void findByNameAndVersionReturnsModel() throws Exception {
        Paths.get(modelStoragePath, "test-model", "2").toFile().mkdirs();
        Files.write(Paths.get(modelStoragePath,"test-model", "2", "model.zip"), "hello-world".getBytes());

        try (InputStream modelStream = modelDataRepository.findByNameAndVersion("test-model", 2)) {
            assertThat(modelStream, not(nullValue()));
            modelStream.close();
        }
    }

    @Test
    public void deleteRemovesFolderFromDisk() throws Exception {
        File modelDirectory = Paths.get(modelStoragePath, "test-model", "3").toFile();
        modelDirectory.mkdirs();

        Files.write(Paths.get(modelStoragePath,"test-model", "3", "model.zip"), "hello-world".getBytes());

        modelDataRepository.delete("test-model", 3);

        assertThat(modelDirectory.exists(), equalTo(false));
    }
}
