package com.anveloper.instagramclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequestDTO {
  private String username;
  private String email;
  private String introduce;
  private MultipartFile profileImage;
}
