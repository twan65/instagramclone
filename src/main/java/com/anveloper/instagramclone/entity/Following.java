package com.anveloper.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("SubscribedToUser")
public class Following {
  @Id
  private String id;
  private String userId;
  private String introduce;
  private String profileUrl;
}