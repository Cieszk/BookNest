package pl.cieszk.booknest.features.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.booknest.features.loan.domain.dto.BookLoanResponseDto;
import pl.cieszk.booknest.features.shared.BookUserDto;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class BookLoanController {
    private final BookLoanService bookLoanService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<BookLoanResponseDto> borrowBook(@RequestBody BookUserDto bookUserDto) {
        BookLoanResponseDto bookLoan = bookLoanService.borrowBook(bookUserDto);
        return ResponseEntity.ok(bookLoan);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        bookLoanService.returnLoan(id);
        return ResponseEntity.noContent().build();
    }
}
