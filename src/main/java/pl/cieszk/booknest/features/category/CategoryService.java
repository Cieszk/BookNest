package pl.cieszk.booknest.features.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.author.mapper.AuthorMapper;
import pl.cieszk.booknest.features.category.domain.Category;
import pl.cieszk.booknest.features.category.domain.dto.CategoryRequestDto;
import pl.cieszk.booknest.features.category.domain.dto.CategoryResponseDto;
import pl.cieszk.booknest.features.category.domain.dto.CategorySummaryDto;
import pl.cieszk.booknest.features.category.mapper.CategoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategorySummaryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toSummaryDtoList(categories);
    }

    public CategoryResponseDto getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + " not found"));
        return categoryMapper.toResponseDto(category);
    }

    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        category = categoryRepository.save(category);
        return categoryMapper.toResponseDto(category);
    }

    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + " not found"));
        categoryMapper.updateEntity(category, categoryRequestDto);
        category = categoryRepository.save(category);
        return categoryMapper.toResponseDto(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id: " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }
}
