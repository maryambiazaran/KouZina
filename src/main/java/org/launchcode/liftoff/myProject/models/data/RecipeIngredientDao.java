package org.launchcode.liftoff.myProject.models.data;

import org.launchcode.liftoff.myProject.models.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RecipeIngredientDao extends CrudRepository<RecipeIngredient,Integer> {

}
