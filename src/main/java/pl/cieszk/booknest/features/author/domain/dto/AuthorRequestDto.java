package pl.cieszk.booknest.features.author.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
    private String firstName;
    private String lastName;
    private String nationality;
    private String biography;
}
