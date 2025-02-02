package pl.cieszk.booknest.features.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;
import pl.cieszk.booknest.features.book.domain.dto.BookRequestDto;
import pl.cieszk.booknest.features.book.domain.dto.BookResponseDto;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

}
