package pl.cieszk.booknest.features.category.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategorySummaryDto {
    Long categoryId;
    String name;
    String description;
}
