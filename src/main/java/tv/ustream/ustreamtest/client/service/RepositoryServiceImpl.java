/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.ustream.ustreamtest.client.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tv.ustream.ustreamtest.client.model.Repository;

/**
 *
 * @author pfeiferlaszlo
 */

@Service
public class RepositoryServiceImpl implements RepositoryService{
    
    private static final String URL_FOR_GET = "http://localhost:8080/repository/";
    private static final String URL_FOR_DELETE = "http://localhost:8080/repository/";
    private static final String URL_FOR_POST = "http://localhost:8080/repository";
    private static final String URL_FOR_LIST = "http://localhost:8080/repository?threshold=";
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void createRepository(String name, String author) {
        CreateRepositoryDTO dto = new CreateRepositoryDTO();
        dto.setName(name);
        dto.setCreator(author);
        try {
            restTemplate.postForEntity(URL_FOR_POST, dto, Object.class);
        } catch (Exception e) {
            System.err.println("This name is already used!");
        }
    }

    @Override
    public Repository getRepository(String name) {
        try {
            return restTemplate.getForObject(URL_FOR_GET + name, Repository.class);
        } catch (Exception e) {
            System.err.println("Threshold should be 0 or greater!");
        }
        return null;
    }

    @Override
    public void deleteRepository(String name) {
        try {
            restTemplate.delete(URL_FOR_DELETE + name);
        } catch (Exception e) {
            System.err.println("No repository with this name!");
        }
    }

    @Override
    public List<Repository> getRepositories(int threshold) {
        try {
            ResponseEntity<List<Repository>> response =
            restTemplate.exchange(URL_FOR_LIST+threshold,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Repository>>() {
                });
            return response.getBody();
        } catch (Exception e) {
            System.err.println("No repository with this name!");
        }
        return null;
    }
    
}
