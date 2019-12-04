package com.huynguyen.videosharing.provider;

import java.util.Map;
import org.springframework.security.core.Authentication;

public interface TokenProvider {

  public String generate(Authentication authentication);

  public boolean verify(String jwtToken);

  public Map<String, Object> parse(String jwtToken);
}
