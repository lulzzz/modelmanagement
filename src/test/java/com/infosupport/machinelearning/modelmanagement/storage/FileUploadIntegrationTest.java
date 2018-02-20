package com.infosupport.machinelearning.modelmanagement.storage;


import com.infosupport.machinelearning.modelmanagement.controllers.ModelsController;
import com.infosupport.machinelearning.modelmanagement.models.Model;
import com.infosupport.machinelearning.modelmanagement.repositories.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadIntegrationTest {

    @MockBean
    private ModelRepository modelRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldUploadFile() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream");

        HttpEntity entity = new HttpEntity("NN", headers);

        ResponseEntity<String> response = restTemplate.exchange("/models/NN", HttpMethod.POST, entity, String.class);
        HttpStatus actual = response.getStatusCode();
        assertEquals(actual, HttpStatus.ACCEPTED);
    }
}
