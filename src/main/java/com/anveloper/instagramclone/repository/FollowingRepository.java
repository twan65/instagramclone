package com.anveloper.instagramclone.repository;

import com.anveloper.instagramclone.entity.Following;
import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowingRepository extends MongoRepository<Following, String> {

  Set<Following> findByUserId(String userId);
}
