package com.anveloper.instagramclone.dto;

public class HomeResponseDTO {
  private String message;

  public HomeResponseDTO(String message) {
    super();
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
