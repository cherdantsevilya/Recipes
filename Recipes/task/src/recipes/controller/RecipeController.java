package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
public class RecipeController {
    private final RecipeService recipeService;
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/api/recipe/new")
    public String postRecipe(Authentication authentication, @Valid @RequestBody Recipe recipe) {
        Recipe recipe1 = new Recipe(recipe.getName(), recipe.getCategory(),
                LocalDateTime.now(), recipe.getDescription(),
                recipe.getIngredients(), recipe.getDirections(),
                authentication.getName());
        recipeService.saveRecipe(recipe1);
        return String.format("{ \"id\":%s }", recipe1.getId());
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.findRecipeById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.findRecipeById(id);
        if (recipe.isPresent()) {
            if (userDetails.getUsername().equals(recipe.get().getEmail())) {
                recipeService.deleteRecipeById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own recipes");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @Valid @RequestBody Recipe recipeAfterUpdate) {
        Optional<Recipe> recipeBeforeUpdate = recipeService.findRecipeById(id);
        if (recipeBeforeUpdate.isPresent()) {
            if (userDetails.getUsername().equals(recipeBeforeUpdate.get().getEmail())) {
                recipeService.updateRecipe(recipeBeforeUpdate.get(), recipeAfterUpdate);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own recipes");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
    }

    @GetMapping(value = "/api/recipe/search", params = "name")
    public List<Recipe> getAllRecipeByName(@RequestParam String name) {
        return recipeService.findAllByName(name);
    }

    @GetMapping(value = "/api/recipe/search", params = "category")
    public List<Recipe> getAllRecipeByCategory(@RequestParam String category) {
        return recipeService.findAllByCategory(category);
    }
}
