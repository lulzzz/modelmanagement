package com.infosupport.machinelearning.modelmanagement.storage.repository;

import com.infosupport.machinelearning.modelmanagement.models.Model;
import org.springframework.data.repository.CrudRepository;


public interface ModelRepository extends CrudRepository <Model, Long>{

    public Model findModelByName(String name);

    public Model findTopByNameOrderByVersionDesc(String name);

}
