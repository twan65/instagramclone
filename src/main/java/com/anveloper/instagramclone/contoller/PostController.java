package com.anveloper.instagramclone.contoller;

import com.anveloper.instagramclone.dto.PostDTO;
import com.anveloper.instagramclone.entity.Post;
import com.anveloper.instagramclone.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Post> findByUserId(@PathVariable("userId") String userId) {
    return postService.findByUserId(userId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void uploadPost(@RequestBody PostDTO postDTO) {
    postService.uploadPost(postDTO);
  }


}
