package com.infosupport.machinelearning.modelmanagement.storage;

import com.infosupport.machinelearning.modelmanagement.models.Model;
import com.infosupport.machinelearning.modelmanagement.repositories.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ModelRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Mock
    private ModelRepository modelRepository;

    @Test
    public void testExample() {
        this.entityManager.persist(new Model("NN", "models" + File.separator + "NN" + File.separator + "NN_1.zip", 1, new Date()));
        Model model = this.modelRepository.findModelByName("NN");
        assertThat(model.getName()).isEqualTo("NN");
    }


    @Test
    public void loadNonExistent() {
        //assertThat(service.load("foo.txt")).doesNotExist()
    }

    @Test
    public void saveAndLoad() {

    }
    /*
            service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes()));
        assertThat(service.load("foo.txt")).exists();

     */

    @Test//(expected = RuntimeException.class)
    public void saveNotPermitted() {

    }
    /*
            service.store(new MockMultipartFile("foo", "../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));

     */

    @Test
    public void savePermitted() {

    }
    /*
            service.store(new MockMultipartFile("foo", "bar/../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));

     */

}
