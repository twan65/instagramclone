package com.anveloper.instagramclone.entity;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("User")
public class User {

  @Id
  private String id;
  private String username;
  private String email;
  private String introduce;
  private String profileUrl;
  private Set<Following> followingToUsers;
  private Set<Follower> followers;

}
