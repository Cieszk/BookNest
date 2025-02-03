package pl.cieszk.booknest.features.publisher.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponseDto {
    private Long publisherId;
    private String name;
    private String address;
    private String website;
    private String contactNumber;
    private List<BookListDto> books;
}
