package com.anveloper.instagramclone.service;

import com.anveloper.instagramclone.common.service.S3Service;
import com.anveloper.instagramclone.dto.UserSaveRequestDTO;
import com.anveloper.instagramclone.entity.User;
import com.anveloper.instagramclone.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final S3Service s3Service;

  @Transactional(readOnly = true)
  public User findByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません。username = " + username));
  }

  @Transactional
  public void save(UserSaveRequestDTO userSaveRequestDTO) {
    Optional<User> savedUser = userRepository.findByUsername(userSaveRequestDTO.getUsername());
    if (savedUser.isPresent()) {
      throw new IllegalArgumentException("ユーザーがすでに登録されています。username = " + userSaveRequestDTO.getUsername());
    }

    String profileUrl = s3Service.uploadFile(userSaveRequestDTO.getProfileImage());

    User user = new User();
    user.setUsername(userSaveRequestDTO.getUsername());
    user.setIntroduce(userSaveRequestDTO.getIntroduce());
    user.setEmail(userSaveRequestDTO.getEmail());
    user.setProfileUrl(profileUrl);

    userRepository.save(user);
  }

  @Transactional
  public void edit(UserSaveRequestDTO userSaveRequestDTO) {
    User savedUser =  userRepository
        .findByUsername(user.getUsername())
        .orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません。username = " + user.getUsername()));

    String profileUrl = s3Service.uploadFile(userSaveRequestDTO.getProfileImage());

    savedUser.setIntroduce(user.getIntroduce());
    savedUser.setProfileUrl();
  }
}
