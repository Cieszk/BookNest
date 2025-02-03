package pl.cieszk.booknest.features.publisher.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherListDto {
    private String name;
    private String address;
    private String website;
    private String contactNumber;
}
