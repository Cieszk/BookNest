package pl.cieszk.booknest.features.reservation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private Long reservationId;
    private LocalDateTime reservationDate;
    private LocalDateTime returnDate;
    private LocalDateTime dueDate;
}
