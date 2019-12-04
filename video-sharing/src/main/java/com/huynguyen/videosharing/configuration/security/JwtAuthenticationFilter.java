package com.huynguyen.videosharing.configuration.security;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.provider.TokenProvider;
import com.huynguyen.videosharing.services.UserService;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final TokenProvider tokenProvider;
  private final UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    try {

      String jwt = getJWTFromRequest(httpServletRequest);

      if (!StringUtils.hasText(jwt) || (StringUtils.hasText(jwt) && !tokenProvider.verify(jwt))) {
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        return;
      }

      Map<String, Object> claims = tokenProvider.parse(jwt);
      Long id = Long.valueOf(claims.get("id").toString());

      Optional<User> user = userService.get(id);

      if (!user.isPresent()) {
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        return;
      }
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          user.get(), null, Collections.emptyList());

      authentication
          .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
      SecurityContextHolder.getContext().setAuthentication(authentication);


    } catch (Exception ex) {
      logger.error("Could not set user authentication in security context", ex);
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private String getJWTFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
      return bearerToken.substring(7);
    }

    return null;
  }

}
