package com.huynguyen.videosharing.mapper.videos;

import com.huynguyen.videosharing.domain.dto.request.VideoDTO;
import com.huynguyen.videosharing.domain.dto.response.VideoResponseDTO;
import com.huynguyen.videosharing.domain.model.Video;
import com.huynguyen.videosharing.mapper.RequestMapper;
import com.huynguyen.videosharing.mapper.ResponseMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VideoMapper extends RequestMapper<VideoDTO, Video>,
    ResponseMapper<Video, VideoResponseDTO> {
  @Mappings({
      @Mapping(target = "user", ignore = true),
      @Mapping(target = "id", ignore = true)
  })
  Video transform(VideoDTO request);

  VideoResponseDTO transform(Video domain);
}
