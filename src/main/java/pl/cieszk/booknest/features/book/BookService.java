package pl.cieszk.booknest.features.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookListDto> getAllBooks() {
        return bookRepository.findAllBooksForList();
    }
}
