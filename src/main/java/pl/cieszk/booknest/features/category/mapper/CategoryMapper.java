package pl.cieszk.booknest.features.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.cieszk.booknest.features.category.domain.Category;
import pl.cieszk.booknest.features.category.domain.dto.CategoryRequestDto;
import pl.cieszk.booknest.features.category.domain.dto.CategoryResponseDto;
import pl.cieszk.booknest.features.category.domain.dto.CategorySummaryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategorySummaryDto> toSummaryDtoList(List<Category> categories);

    CategoryResponseDto toResponseDto(Category category);

    Category toEntity(CategoryRequestDto categoryRequestDto);

    void updateEntity(@MappingTarget Category category, CategoryRequestDto categoryRequestDto);
}
