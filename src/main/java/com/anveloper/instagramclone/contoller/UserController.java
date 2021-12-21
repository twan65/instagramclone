package com.anveloper.instagramclone.contoller;

import com.anveloper.instagramclone.dto.UserEditRequestDTO;
import com.anveloper.instagramclone.dto.UserSaveRequestDTO;
import com.anveloper.instagramclone.entity.User;
import com.anveloper.instagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
  public void saveUser(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
    userService.saveUser(userSaveRequestDTO);
  }

  // TODO: Security適用後、修正
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void editUser(@RequestBody UserEditRequestDTO userEditRequestDTO) {
    userService.editUser(userEditRequestDTO);
  }

  // TODO: Security適用後、修正
  @PutMapping("/image")
  @ResponseStatus(HttpStatus.OK)
  public void editImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
    userService.editProfileImage(file, userId);
  }

  // TODO: Security適用後、修正
  @PostMapping("/follower/{followerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void saveFollower(@PathVariable("followerId") String followerId) {
    userService.saveFollower(followerId);
  }

  // TODO: Security適用後、修正
  @DeleteMapping("/unfollower/{followerId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteFollower(@PathVariable("followerId") String followerId) {
    userService.deleteFollower(followerId);
  }
}
