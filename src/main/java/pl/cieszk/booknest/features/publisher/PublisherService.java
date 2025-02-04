package pl.cieszk.booknest.features.publisher;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.publisher.domain.Publisher;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherListDto;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherRequestDto;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherResponseDto;
import pl.cieszk.booknest.features.publisher.mapper.PublisherMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public List<PublisherListDto> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publisherMapper.toPublisherListDto(publishers);
    }

    public PublisherResponseDto getPublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher with id: " + id + " not found"));
        return publisherMapper.toResponseDto(publisher);
    }

    public PublisherResponseDto updatePublisher(PublisherRequestDto publisher, Long id) {
        Publisher publisherToUpdate = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher with id: " + id + " not found"));
        publisherMapper.updateEntity(publisherToUpdate, publisher);
        publisherToUpdate = publisherRepository.save(publisherToUpdate);
        return publisherMapper.toResponseDto(publisherToUpdate);
    }

    public PublisherResponseDto createPublisher(PublisherRequestDto request) {
        if (publisherRepository.existsByName(request.getName())) {
            throw new IllegalStateException("Publisher with given name already exists");
        }
        Publisher publisher = publisherMapper.toEntity(request);
        publisher = publisherRepository.save(publisher);
        return publisherMapper.toResponseDto(publisher);
    }

    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new EntityNotFoundException("Publisher with id: " + id + " not found");
        }
        publisherRepository.deleteById(id);
    }
}
