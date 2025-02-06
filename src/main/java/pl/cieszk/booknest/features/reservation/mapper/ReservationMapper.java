package pl.cieszk.booknest.features.reservation.mapper;

import org.mapstruct.Mapper;
import pl.cieszk.booknest.features.reservation.domain.Reservation;
import pl.cieszk.booknest.features.reservation.domain.dto.ReservationResponseDto;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationResponseDto toResponseDto(Reservation reservation);
}
