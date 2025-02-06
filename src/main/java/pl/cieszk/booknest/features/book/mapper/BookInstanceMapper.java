package pl.cieszk.booknest.features.book.mapper;

import org.mapstruct.Mapper;
import pl.cieszk.booknest.features.book.domain.BookInstance;
import pl.cieszk.booknest.features.book.domain.dto.BookInstanceSummaryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookInstanceMapper {
    List<BookInstanceSummaryDto> toResponseDtoList(List<BookInstance> instances);
}
