package com.anveloper.instagramclone.repository;

import com.anveloper.instagramclone.entity.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

  List<Post> findByUserId(String UserId);

}
