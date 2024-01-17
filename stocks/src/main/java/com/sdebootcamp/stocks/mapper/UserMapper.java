package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.UsersDto;
import com.sdebootcamp.stocks.entity.Users;
import org.mapstruct.Mapper;
@Mapper
public interface UserMapper {
  Users UsersDtoToUsers(UsersDto usersDto);
  UsersDto UsersToUsersDto(Users users);

}
