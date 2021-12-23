package com.anveloper.instagramclone.common.config.auth;

import com.anveloper.instagramclone.common.config.auth.dto.SecurityUserDetails;
import com.anveloper.instagramclone.entity.User;
import com.anveloper.instagramclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtAuthProcessor {

  private static final String HTTP_HEADER = "Authorization";

  @Autowired
  private UserRepository userRepository;

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
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, String> map = objectMapper.readValue(json, HashMap.class);

      String email = map.get("email");
      Optional<User> savedUser = userRepository.findByEmail(email);
      if (savedUser.isPresent()) {
        String username = savedUser.get().getUsername();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(
                String.valueOf(username + "::" + email),
                null, grantedAuthorities);

        SecurityUserDetails userDetails = new SecurityUserDetails(username, email);
        token.setDetails(userDetails);
        return token;
      }

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
