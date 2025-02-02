package pl.cieszk.booknest.features.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;
import pl.cieszk.booknest.features.book.domain.dto.BookResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    ResponseEntity<List<BookListDto>> getAllBooks() {
        List<BookListDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
        BookResponseDto dto = bookService.getBook(id);
        return ResponseEntity.ok(dto);
    }
}
