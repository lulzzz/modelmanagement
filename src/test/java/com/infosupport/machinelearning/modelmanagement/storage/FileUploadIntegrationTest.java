package com.infosupport.machinelearning.modelmanagement.storage;


import com.infosupport.machinelearning.modelmanagement.controllers.ModelsController;
import com.infosupport.machinelearning.modelmanagement.storage.repository.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(ModelsController.class)
@SpringBootTest
public class FileUploadIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ModelRepository modelRepository;

    @LocalServerPort
    private int port;

    @Test
    public void shouldUploadFile() throws Exception {
        //TODO: TEST!
    }
    /*
    	@Test
	public void shouldUploadFile() throws Exception {
		ClassPathResource resource = new ClassPathResource("/com.infosupport.machinelearning.modelmanagement/testupload.txt", getClass());

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("file", resource);
		ResponseEntity<String> response = this.restTemplate.postForEntity("/", map,
				String.class);

		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.FOUND);
		assertThat(response.getHeaders().getLocation().toString())
				.startsWith("http://localhost:" + this.port + "/");
		then(storageService).should().store(any(MultipartFile.class));
	}
    
     */

    @Test
    public void shouldDownloadFile() throws Exception {
        //TODO: TEST!
    }
    
    /*
    	@Test
	public void shouldDownloadFile() throws Exception {
		ClassPathResource resource = new ClassPathResource("/com.infosupport.machinelearning.modelmanagement/testupload.txt", getClass());
		given(this.storageService.loadAsResource("testupload.txt")).willReturn(resource);

		ResponseEntity<String> response = this.restTemplate
				.getForEntity("/files/{filename}", String.class, "testupload.txt");

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION))
				.isEqualTo("attachment; filename=\"testupload.txt\"");
		assertThat(response.getBody()).isEqualTo("Spring Framework");
	}
    
     */

}
