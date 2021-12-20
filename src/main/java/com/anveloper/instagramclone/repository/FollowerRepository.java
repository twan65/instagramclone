package com.anveloper.instagramclone.repository;

import com.anveloper.instagramclone.entity.Follower;
import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowerRepository extends MongoRepository<Follower, String> {
  Set<Follower> findByUserId(String userId);
}
