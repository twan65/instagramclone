package com.anveloper.instagramclone.entity;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Post")
public class Post {

  @Id
  private String id;
  private String description;
  private String userId;
  private Integer likes;
  private Set<String> tags;
  private String imageUrl;
  private List<Comment> commentList;

}
