package pl.cieszk.booknest.features.publisher.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherRequestDto {
    private String name;
    private String address;
    private String website;
    private String contactNumber;
    private Set<Long> bookIds;
}
