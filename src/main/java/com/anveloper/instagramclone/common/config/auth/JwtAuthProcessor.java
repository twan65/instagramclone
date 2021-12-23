package com.anveloper.instagramclone.common.config.auth;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthProcessor {

  private static final String HTTP_HEADER = "Authorization";

  @Autowired
  private BeanFactory beanFactory;

  private ConfigurableJWTProcessor<SecurityContext> configurableJwtProcessor;

  public Authentication authenticate(HttpServletRequest request) throws Exception {
    String bearerToken = request.getHeader(HTTP_HEADER);
    if (bearerToken != null) {
      String accessToken = this.getBearerToken(bearerToken);
      if (accessToken == null) {
        return null;
      }

      String json = getUserInfo(bearerToken);
    }
    return null;
  }

  private String getUserInfo(String bearerToken) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet("https://your_domain/userInfo");

    httpGet.addHeader("Content-type", "application/json");
    httpGet.addHeader("Authorization", bearerToken);

    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

    httpClient.close();

    return json;

  }

  private String getBearerToken(String token) {
    return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : null;
  }
}
