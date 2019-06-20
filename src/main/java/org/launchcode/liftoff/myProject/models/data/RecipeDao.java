package org.launchcode.liftoff.myProject.models.data;

import org.launchcode.liftoff.myProject.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RecipeDao extends CrudRepository<Recipe,Integer> {

}
