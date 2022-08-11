package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.entity.Recipe;
import recipes.repository.RecipeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> findRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public List<Recipe> findAllByName(String name) {
        return recipeRepository.findAllByNameContainsIgnoreCaseOrderByDateDesc(name);
    }

    public List<Recipe> findAllByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public void updateRecipe(Recipe recipeBeforeUpdate, Recipe recipeAfterUpdate) {
        recipeBeforeUpdate.setName(recipeAfterUpdate.getName());
        recipeBeforeUpdate.setCategory(recipeAfterUpdate.getCategory());
        recipeBeforeUpdate.setDate(LocalDateTime.now());
        recipeBeforeUpdate.setDescription(recipeAfterUpdate.getDescription());
        recipeBeforeUpdate.setIngredients(recipeAfterUpdate.getIngredients());
        recipeBeforeUpdate.setDirections(recipeAfterUpdate.getDirections());
        recipeRepository.save(recipeBeforeUpdate);
    }
}
