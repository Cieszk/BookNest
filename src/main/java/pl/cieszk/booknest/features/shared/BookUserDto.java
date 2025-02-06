package pl.cieszk.booknest.features.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUserDto {
    private Long bookId;
    private Long userId;
}
