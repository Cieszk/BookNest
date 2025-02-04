package pl.cieszk.booknest.features.publisher.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.cieszk.booknest.features.publisher.domain.Publisher;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherListDto;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherRequestDto;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    List<PublisherListDto> toPublisherListDto(List<Publisher> publishers);

    PublisherResponseDto toResponseDto(Publisher publisher);

    Publisher toEntity(PublisherRequestDto publisherRequestDto);

    void updateEntity(@MappingTarget Publisher publisher, PublisherRequestDto publisherRequestDto);
}
