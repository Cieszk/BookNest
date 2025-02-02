package pl.cieszk.booknest.features.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;
import pl.cieszk.booknest.features.book.domain.dto.BookResponseDto;
import pl.cieszk.booknest.features.book.mapper.BookMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public List<BookListDto> getAllBooks() {
        return bookRepository.findAllBooksForList();
    }

    public BookResponseDto getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        logger.debug("Authors:  " + book.getAuthors());
        return bookMapper.toBookResponseDto(book);
    }
}
