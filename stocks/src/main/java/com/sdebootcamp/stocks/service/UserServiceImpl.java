package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.UsersDto;
import com.sdebootcamp.stocks.entity.Users;
import com.sdebootcamp.stocks.exceptions.UserNotFound;
import com.sdebootcamp.stocks.mapper.UserMapper;
import com.sdebootcamp.stocks.repository.UsersRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserServiceImpl implements UserService{
  @Autowired
  private UsersRepository usersRepository;

  private UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

  @Override
  public UsersDto createUser(UsersDto userDto) {
    Users savedUser = MAPPER.usersDtoToUsers(userDto);
    Users users = usersRepository.save(savedUser);
    System.out.println(users.toString());
    return MAPPER.usersToUsersDto(users);
  }

  @Override
  public UsersDto getUserById(Long userId) throws UserNotFound {
    Optional<Users> optionalUser = usersRepository.findById(userId);
    if(optionalUser.isPresent()) return MAPPER.usersToUsersDto(optionalUser.get());
    throw new UserNotFound("Invalid UserId pass "+userId);
  }

  @Override
  public UsersDto updateUser(Long userId, UsersDto userDto) throws UserNotFound {
    userDto.setUserAccountId(userId);
    Optional<Users> optionalUser = usersRepository.findById(userId);
    if(optionalUser.isPresent()){
      Users updateUser = optionalUser.get();
      updateUser.setUserAccountId(userDto.getUserAccountId());
      updateUser.setUsername(userDto.getUsername());
      updateUser.setPassword(userDto.getPassword());
      return MAPPER.usersToUsersDto(usersRepository.save(updateUser));
    }
    throw new UserNotFound("Invalid UserId Pass"+ userId);
  }


  @Override
  public void deleteUser(Long userId) {


  }
}
