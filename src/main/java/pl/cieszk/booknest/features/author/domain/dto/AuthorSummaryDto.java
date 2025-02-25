package pl.cieszk.booknest.features.author.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSummaryDto {
    Long authorId;
    String firstName;
    String lastName;
}
