package pl.cieszk.booknest.features.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherListDto;

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
}
