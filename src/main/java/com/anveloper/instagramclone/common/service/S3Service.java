package com.anveloper.instagramclone.common.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class S3Service implements FileService{

    private final AmazonS3Client awsS3Client;

    public static final String BUCKET_NAME = "sample-bucket";

    @Override
    public String uploadFile(MultipartFile file) {
      var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

      var key = UUID.randomUUID().toString() + "." + filenameExtension;

      var metadata = new ObjectMetadata();
      metadata.setContentLength(file.getSize());
      metadata.setContentType(file.getContentType());

      try {
        awsS3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
      } catch (IOException ex) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ファイルアップロード失敗");
      }

      awsS3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);

      return awsS3Client.getResourceUrl(BUCKET_NAME, key);
    }
}
