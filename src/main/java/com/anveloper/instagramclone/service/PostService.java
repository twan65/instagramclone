package com.anveloper.instagramclone.service;

import com.anveloper.instagramclone.common.service.S3Service;
import com.anveloper.instagramclone.dto.PostDTO;
import com.anveloper.instagramclone.entity.Post;
import com.anveloper.instagramclone.entity.User;
import com.anveloper.instagramclone.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

  private final S3Service s3Service;
  private final PostRepository postRepository;
  private final UserService userService;

  public List<Post> findByUserId(String userId) {
    userService.findByUserId(userId);
    return postRepository.findByUserId(userId);
  }

  @Transactional
  public void uploadPost(PostDTO postDTO) {
    Post post = new Post();
    post.setUserId(postDTO.getUserId());
    post.setDescription(postDTO.getDescription());
    post.setLikes(0);

    String imageUrl = s3Service.uploadFile(postDTO.getImageFile());
    post.setImageUrl(imageUrl);

    postRepository.save(post);
  }
}
