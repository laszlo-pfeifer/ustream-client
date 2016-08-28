/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.ustream.ustreamtest.client.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tv.ustream.ustreamtest.client.model.Repository;

/**
 *
 * @author pfeiferlaszlo
 */
public class RepositoryServiceImplTest {
    
    private static final String RETURN_NAME = "return-name";
    private static final String TEST_NAME = "test-name";
    private static final String TEST_AUTHOR = "test-author";
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private RepositoryService repositoryService;
    
    @Before
    public void setUp() {
        repositoryService = new RepositoryServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRepository() {
        // WHEN
        repositoryService.createRepository(TEST_NAME, TEST_AUTHOR);
        // THEN
        Mockito.verify(restTemplate).postForEntity(Matchers.eq("http://localhost:8080/repository"), Matchers.any(CreateRepositoryDTO.class), Matchers.any());
    }

    @Test
    public void testGetRepository() {
        // GIVEN
        Repository repository = new Repository();
        repository.setName(RETURN_NAME);
        Mockito.when(restTemplate.getForObject(Matchers.eq("http://localhost:8080/repository/"+TEST_NAME), Matchers.any())).thenReturn(repository);
        // WHEN
        Repository result = repositoryService.getRepository(TEST_NAME);
        // THEN
        Mockito.verify(restTemplate).getForObject(Matchers.eq("http://localhost:8080/repository/"+TEST_NAME), Matchers.any());
        Assert.assertEquals(RETURN_NAME, result.getName());
    }

    @Test
    public void testDeleteRepository() {
        // WHEN
        repositoryService.deleteRepository(TEST_NAME);
        // THEN
        Mockito.verify(restTemplate).delete(Matchers.eq("http://localhost:8080/repository/"+TEST_NAME));
    }

    @Test
    public void testGetRepositories() {
        // GIVEN
        final int threshold = 1;
        List<Repository> repositorys = new ArrayList<>();
        ResponseEntity<List<Repository>> response = ResponseEntity.ok().body(repositorys);
        Repository repository = new Repository();
        repository.setName(RETURN_NAME);
        repositorys.add(repository);
        Mockito.when(restTemplate.exchange(Matchers.eq("http://localhost:8080/repository?threshold=" + threshold), Matchers.eq(HttpMethod.GET), Matchers.any(), Matchers.any(ParameterizedTypeReference.class))).thenReturn(response);
        // WHEN
        List<Repository> results = repositoryService.getRepositories(threshold);
        // THEN
        Mockito.verify(restTemplate).exchange(Matchers.eq("http://localhost:8080/repository?threshold=" + threshold), Matchers.eq(HttpMethod.GET), Matchers.any(), Matchers.any(ParameterizedTypeReference.class));
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(RETURN_NAME, results.get(0).getName());
    }
    
}
