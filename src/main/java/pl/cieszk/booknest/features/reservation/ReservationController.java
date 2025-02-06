package pl.cieszk.booknest.features.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.booknest.features.reservation.domain.dto.CreateReservationDto;
import pl.cieszk.booknest.features.reservation.domain.dto.ReservationResponseDto;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody CreateReservationDto createReservationDto) {
        ReservationResponseDto reservation = reservationService.createReservation(createReservationDto);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/cancel/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
