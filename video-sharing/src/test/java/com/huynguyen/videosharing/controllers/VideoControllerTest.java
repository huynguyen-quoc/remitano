package com.huynguyen.videosharing.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huynguyen.videosharing.domain.dto.request.VideoDTO;
import com.huynguyen.videosharing.domain.dto.response.VideoResponseDTO;
import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.domain.model.Video;
import com.huynguyen.videosharing.mapper.videos.VideoMapper;
import com.huynguyen.videosharing.provider.TokenProvider;
import com.huynguyen.videosharing.services.UserService;
import com.huynguyen.videosharing.services.VideoService;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VideoController.class)
@AutoConfigureMockMvc(addFilters = false)
class VideoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private VideoService videoService;

  @MockBean
  private VideoMapper videoMapper;

  @MockBean
  private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean
  private UserDetailsService userDetailsService;

  @MockBean
  private TokenProvider tokenProvider;

  @MockBean
  private AuthenticationManager authenticationManager;

  @BeforeEach
  void setUp() {
    User principal = mock(User.class);
    Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(authentication);
    SecurityContextHolder.setContext(context);
  }

  @Test
  public void createShouldReturnCorrectUserAndCorrectHttpStatusAndCallServiceATimes()
      throws Exception {

    VideoDTO request = VideoDTO.builder().url("test")
        .description("test_description").title("test_title").build();
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = objectMapper.writeValueAsString(request);
    Video model = mock(Video.class);
    VideoResponseDTO response = VideoResponseDTO.builder().id(model.getId())
        .url("test_url").description("test_description").title("test_title").build();

    when(videoMapper.transform(model)).thenReturn(response);
    when(videoMapper.transform(request)).thenReturn(model);
    when(videoService.create(any(Video.class), any(User.class))).thenReturn(model);

    mockMvc.perform(post("/videos")
        .content(jsonString)
        .with(user(mock(User.class)))
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(response.getId().intValue())))
        .andExpect(jsonPath("$.url", is(response.getUrl())))
        .andExpect(jsonPath("$.title", is(response.getTitle())))
        .andExpect(jsonPath("$.description", is(response.getDescription())));

    verify(videoService, times(1)).create(any(Video.class), any(User.class));
  }


  @Test
  @SuppressWarnings("unchecked")
  public void findAllShouldReturnCorrectPagedVideoAndCallServiceOneTimes() throws Exception {
    Video mockVideo = mock(Video.class);
    VideoResponseDTO response = VideoResponseDTO.builder().id(mockVideo.getId())
        .url("test_url").description("test_description").title("test_title").build();
    Page<Video> mockPage = mock(Page.class);
    when(mockPage.get()).thenReturn(Stream.<Video>builder().add(mockVideo).build());
    when(videoMapper.transform(mockVideo)).thenReturn(response);
    when(videoService.findAll(any(Pageable.class))).thenReturn(mockPage);

    mockMvc.perform(get("/videos")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", hasSize(1)))
        .andExpect(jsonPath("$.data[0].title", is(response.getTitle())))
        .andExpect(jsonPath("$.data[0].description", is(response.getDescription())))
        .andExpect(jsonPath("$.data[0].url", is(response.getUrl())));

    verify(videoService, times(1)).findAll(any(Pageable.class));
  }

}