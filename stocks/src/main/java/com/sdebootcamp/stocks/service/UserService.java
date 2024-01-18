package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.UsersDto;
import com.sdebootcamp.stocks.exceptions.UserNotFound;

public interface UserService {
  UsersDto createUser(UsersDto userDto);
  UsersDto getUserById(Long userId) throws UserNotFound;

  UsersDto updateUser(Long userId, UsersDto userDto) throws UserNotFound;
  void deleteUser(Long userId) throws UserNotFound;

}
