package com.anveloper.instagramclone.contoller;

import com.anveloper.instagramclone.dto.HomeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
  @GetMapping(value = "/private")
  public ResponseEntity<HomeResponseDTO> privateEndpoint() {
    return ResponseEntity.ok(new HomeResponseDTO("Private Endpoint Working fine !"));
  }
}
