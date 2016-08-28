/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.ustream.ustreamtest.client.service;

import java.util.List;
import tv.ustream.ustreamtest.client.model.Repository;

/**
 *
 * @author pfeiferlaszlo
 */
public interface RepositoryService {

    public void createRepository(String name, String author);

    public Repository getRepository(String name);

    public void deleteRepository(String name);

    public List<Repository> getRepositories(int threshold);
    
}
