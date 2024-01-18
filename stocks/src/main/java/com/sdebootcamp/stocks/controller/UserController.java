package com.sdebootcamp.stocks.controller;

import com.sdebootcamp.stocks.dto.UsersDto;
import com.sdebootcamp.stocks.exceptions.UserNotFound;
import com.sdebootcamp.stocks.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/")
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto userDto){
    UsersDto savedUser = userService.createUser(userDto);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<UsersDto> getUserById(@PathVariable("id") Long userId) throws UserNotFound {
    UsersDto user = userService.getUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<UsersDto> updateuser(@PathVariable("id") Long userId, @RequestBody UsersDto userDto) throws UserNotFound{
    userDto.setUserAccountId(userId);
    UsersDto user = userService.updateUser(userId,userDto);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
    userService.deleteUser(userId);
    return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
  }

}
