package pl.cieszk.booknest.features.reservation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDto {

    private Long bookId;
    private Long userId;
    private LocalDateTime reservationDate; // Optional
    private LocalDateTime dueDate; // Optional
}
