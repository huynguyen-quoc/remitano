package com.huynguyen.videosharing.mapper.users;

import com.huynguyen.videosharing.domain.dto.request.UserDTO;
import com.huynguyen.videosharing.domain.dto.response.UserResponseDTO;
import com.huynguyen.videosharing.domain.enums.UserStatus;
import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.mapper.RequestMapper;
import com.huynguyen.videosharing.mapper.ResponseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UsersMapper extends RequestMapper<UserDTO, User>,
    ResponseMapper<User, UserResponseDTO> {

  @Mappings({
      @Mapping(target = "status", ignore = true),
      @Mapping(target = "id", ignore = true)
  })
  User transform(UserDTO request);

  @AfterMapping
  default void addCreatedStatus(UserDTO request, @MappingTarget User user) {
    user.setStatus(UserStatus.CREATED);
  }

  UserResponseDTO transform(User domain);
}
