package com.huynguyen.videosharing.controllers;

import com.huynguyen.videosharing.domain.dto.request.VideoDTO;
import com.huynguyen.videosharing.domain.dto.response.PageDataResponse;
import com.huynguyen.videosharing.domain.dto.response.PageResponse;
import com.huynguyen.videosharing.domain.dto.response.VideoResponseDTO;
import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.domain.model.Video;
import com.huynguyen.videosharing.mapper.videos.VideoMapper;
import com.huynguyen.videosharing.services.VideoService;
import com.huynguyen.videosharing.utils.ResponseFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

  private final VideoMapper mapper;
  private final VideoService service;

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<VideoResponseDTO> create(
      @RequestBody VideoDTO request, @AuthenticationPrincipal User user) {
    Video model = mapper.transform(request);
    log.info("create videos with info {} and user {}", model, user);
    VideoResponseDTO response = mapper
        .transform(service.create(model, user));
    return ResponseFactory.created(response);
  }

  @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  @Transactional(readOnly = true)
  public ResponseEntity<PageDataResponse<VideoResponseDTO>> findAll(
      @RequestParam(value = "page_index", required = false) Integer pageIndex,
      @RequestParam(value = "page_size", required = false) Integer pageSize) {
    log.info("list videos with page");
    pageIndex = pageIndex != null ? pageIndex : 0;
    pageSize = pageSize != null && pageSize <= 50 ? pageSize : 50;
    PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
    Page<Video> videoPage = service.findAll(pageRequest);
    List<VideoResponseDTO> videos = videoPage.get().map(
        mapper::transform).collect(Collectors.toList());
    PageResponse pageResponse = PageResponse.builder().currentPage(videoPage.getNumber())
        .hasNext(videoPage.hasNext())
        .hasPrevious(videoPage.hasPrevious()).totalElements(videoPage.getTotalElements())
        .totalPages(videoPage.getTotalPages()).build();
    PageDataResponse<VideoResponseDTO> response = PageDataResponse.<VideoResponseDTO>builder()
        .data(videos).page(pageResponse).build();
    return ResponseFactory.success(response);
  }


}
