package pl.cieszk.booknest.features.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherListDto;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherRequestDto;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<PublisherListDto>> getAllPublishers() {
        List<PublisherListDto> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PublisherResponseDto> getPublisher(@PathVariable Long id) {
        PublisherResponseDto publisher = publisherService.getPublisher(id);
        return ResponseEntity.ok(publisher);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublisherResponseDto> addPublisher(@RequestBody PublisherRequestDto request) {
        PublisherResponseDto publisher = publisherService.createPublisher(request);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublisherResponseDto> updatePublisher(@RequestBody PublisherRequestDto request, @PathVariable Long id) {
        PublisherResponseDto publisher = publisherService.updatePublisher(request, id);
        return ResponseEntity.ok(publisher);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
