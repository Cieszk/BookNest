package pl.cieszk.booknest.features.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.cieszk.booknest.features.auth.domain.User;
import pl.cieszk.booknest.features.auth.domain.dto.AuthResponse;
import pl.cieszk.booknest.features.auth.domain.dto.RegisterRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User registerRequestToUser(RegisterRequest registerRequest);

}
