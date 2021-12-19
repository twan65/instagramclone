package com.anveloper.instagramclone.contoller;

import com.anveloper.instagramclone.dto.UserEditRequestDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/{userid}")
  @ResponseStatus(HttpStatus.OK)
  public User findByUsername(@PathVariable("userid") String userid) {
    return userService.findByUserId(userid);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void saveUser(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
    userService.saveUser(userSaveRequestDTO);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void editUser(@RequestBody UserEditRequestDTO userEditRequestDTO) {
    userService.editUser(userEditRequestDTO);
  }

  @PutMapping("/image")
  @ResponseStatus(HttpStatus.OK)
  public void editProfileImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
    userService.editProfileImage(file, userId);
  }

}
