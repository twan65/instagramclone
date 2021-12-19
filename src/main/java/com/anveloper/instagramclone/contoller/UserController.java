package com.anveloper.instagramclone.contoller;

import com.anveloper.instagramclone.dto.UserSaveRequestDTO;
import com.anveloper.instagramclone.entity.User;
import com.anveloper.instagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/{username}")
  @ResponseStatus(HttpStatus.OK)
  public User findByUsername(@PathVariable("username") String username) {
    return userService.findByUsername(username);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
    userService.save(userSaveRequestDTO);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public User edit(@RequestBody User user) {
    userService.edit(user);
  }


}