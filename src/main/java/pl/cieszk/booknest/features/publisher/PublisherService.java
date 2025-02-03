package pl.cieszk.booknest.features.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.publisher.domain.Publisher;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherListDto;
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
}
