package recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NonNull
    @UpdateTimestamp
    private LocalDateTime date;

    @NotBlank
    private String description;

    @NotEmpty
    @ElementCollection
    private List<String> ingredients;

    @NotEmpty
    @ElementCollection
    private List<String> directions;

    @JsonIgnore
    private String email;

    public Recipe(String name, String category, @NonNull LocalDateTime date, String description, List<String> ingredients, List<String> directions, String email) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.email = email;
    }
}
