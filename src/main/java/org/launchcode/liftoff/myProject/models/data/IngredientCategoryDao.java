package org.launchcode.liftoff.myProject.models.data;

import org.launchcode.liftoff.myProject.models.IngredientCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IngredientCategoryDao extends CrudRepository<IngredientCategory,Integer> {

}
