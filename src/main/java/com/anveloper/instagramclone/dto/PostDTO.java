package com.anveloper.instagramclone.dto;

import com.anveloper.instagramclone.entity.Comment;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
  private String id;
  private String description;
  private String userId;
  private Integer likes;
  private MultipartFile imageFile;
  private Set<String> tags;
  private List<Comment> commentList;
}
