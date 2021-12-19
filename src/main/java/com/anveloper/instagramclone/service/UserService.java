package com.anveloper.instagramclone.service;

import com.anveloper.instagramclone.common.service.S3Service;
import com.anveloper.instagramclone.dto.UserEditRequestDTO;
import com.anveloper.instagramclone.dto.UserSaveRequestDTO;
import com.anveloper.instagramclone.entity.User;
import com.anveloper.instagramclone.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final S3Service s3Service;

  @Transactional(readOnly = true)
  public User findByUserId(String userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません。userId = " + userId));
  }

  @Transactional
  public void saveUser(UserSaveRequestDTO userSaveRequestDTO) {
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
  public void editUser(UserEditRequestDTO userEditRequestDTO) {
    User savedUser =  userRepository
        .findById(userEditRequestDTO.getId())
        .orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません。userId = " + userEditRequestDTO.getId()));

    savedUser.setIntroduce(userEditRequestDTO.getIntroduce());
  }

  @Transactional
  public void editProfileImage(MultipartFile file, String userId) {
    User savedUser =  userRepository
        .findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません。userId = " + userId));

    String profileUrl = s3Service.uploadFile(file);
    savedUser.setProfileUrl(profileUrl);

    userRepository.save(savedUser);
  }
}
