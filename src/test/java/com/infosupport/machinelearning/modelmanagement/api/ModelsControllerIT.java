package com.infosupport.machinelearning.modelmanagement.api;

import com.infosupport.machinelearning.modelmanagement.storage.ModelStorageService;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ModelsControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ModelStorageService modelStorageService;

    @Value("${modelmanagement.storage.rootpath}")
    private String modelRepositoryPath;

    @After
    public void tearDown() throws Exception {
        Paths.get(modelRepositoryPath).toFile().delete();
    }

    @Test
    public void shouldUploadFileSuccesfully() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream");

        HttpEntity entity = new HttpEntity("NN", headers);

        ResponseEntity<String> response = restTemplate.exchange("/models/NN", HttpMethod.POST, entity, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.ACCEPTED));
    }

    @Test
    public void shouldDownloadModelsSuccesfully() throws Exception {

        InputStream modelStream = new ByteArrayInputStream("hello-world".getBytes());
        modelStorageService.saveModel("test-model", modelStream);

        HttpEntity<String> request = buildModelDownloadRequestEntity();

        ResponseEntity<byte[]> response = restTemplate.exchange("/models/test-model/1", HttpMethod.GET, request, byte[].class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void shouldReturn404ForNonExistingModels() {
        HttpEntity<String> request = buildModelDownloadRequestEntity();
        ResponseEntity<byte[]> response = restTemplate.exchange("/models/test-model/2", HttpMethod.GET, request, byte[].class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @NotNull
    private HttpEntity<String> buildModelDownloadRequestEntity() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));

        return new HttpEntity<>(headers);
    }
}
