package pl.cieszk.booknest.features.loan.mapper;

import org.mapstruct.Mapper;
import pl.cieszk.booknest.features.loan.domain.BookLoan;
import pl.cieszk.booknest.features.loan.domain.dto.BookLoanResponseDto;

@Mapper(componentModel = "spring")
public interface BookLoanMapper {

    BookLoanResponseDto toResponseDto(BookLoan loan);
}
