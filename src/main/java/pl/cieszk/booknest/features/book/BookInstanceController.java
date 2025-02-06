package pl.cieszk.booknest.features.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.booknest.features.book.domain.dto.BookInstanceRequestDto;
import pl.cieszk.booknest.features.book.domain.dto.BookInstanceSummaryDto;

import java.util.List;

@RestController
@RequestMapping("/api/book-instances")
@RequiredArgsConstructor
public class BookInstanceController {
    private final BookInstanceService bookInstanceService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookInstanceSummaryDto> addBookInstance(@RequestBody BookInstanceRequestDto bookInstanceRequestDto) {
        BookInstanceSummaryDto bookInstance = bookInstanceService.addBookInstance(bookInstanceRequestDto);
        return ResponseEntity.ok(bookInstance);
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateBookInstanceStatus(@PathVariable Long id, @RequestBody BookInstanceRequestDto bookInstanceRequestDto) {
        bookInstanceService.updateBookInstanceStatus(id, bookInstanceRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookInstanceSummaryDto>> getAllInstancesForTheBook(@PathVariable Long id) {
        List<BookInstanceSummaryDto> instances = bookInstanceService.getAllInstancesForTheBook(id);
        return ResponseEntity.ok(instances);
    }
}
