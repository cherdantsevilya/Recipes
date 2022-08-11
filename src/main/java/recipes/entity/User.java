package recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_")
public class User {

    @Id
    @NotBlank
    @Pattern(regexp = ".+@.+\\..+")
    @Column(name = "EMAIL_")
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name = "PASS_")
    private String password;
}
