package com.anveloper.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Comment")
public class Comment {

  @Id
  private String id;
  private String text;
  private String authorId;
  private Integer likeCount;
}
