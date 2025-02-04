package pl.cieszk.booknest.features.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.booknest.features.author.domain.dto.AuthorRequestDto;
import pl.cieszk.booknest.features.author.domain.dto.AuthorResponseDto;
import pl.cieszk.booknest.features.author.domain.dto.AuthorSummaryDto;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AuthorSummaryDto>> getAllAuthors() {
        List<AuthorSummaryDto> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable Long id) {
        AuthorResponseDto author = authorService.getAuthor(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody AuthorRequestDto authorRequestDto) {
        AuthorResponseDto author = authorService.addAuthor(authorRequestDto);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@RequestBody AuthorRequestDto authorRequestDto, @PathVariable Long id) {
        AuthorResponseDto author = authorService.updateAuthor(authorRequestDto, id);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
