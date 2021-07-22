package com.gdrc.gamehall.server.persistence.mapper;

import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "name")
    User toUser(UserEntity userEntity);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(User user);
}
