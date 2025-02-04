package pl.cieszk.booknest.features.author.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.cieszk.booknest.features.author.domain.Author;
import pl.cieszk.booknest.features.author.domain.dto.AuthorRequestDto;
import pl.cieszk.booknest.features.author.domain.dto.AuthorResponseDto;
import pl.cieszk.booknest.features.author.domain.dto.AuthorSummaryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDto toResponseDto(Author author);

    List<AuthorSummaryDto> toSummaryDtoList(List<Author> authors);

    Author toEntity(AuthorRequestDto authorRequestDto);

    void updateEntity(@MappingTarget Author author, AuthorRequestDto authorRequestDto);
}
