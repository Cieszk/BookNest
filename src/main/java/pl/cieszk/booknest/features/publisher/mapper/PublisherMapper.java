package pl.cieszk.booknest.features.publisher.mapper;


import org.mapstruct.Mapper;
import pl.cieszk.booknest.features.publisher.domain.Publisher;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherListDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    List<PublisherListDto> toPublisherListDto(List<Publisher> publishers);
}
