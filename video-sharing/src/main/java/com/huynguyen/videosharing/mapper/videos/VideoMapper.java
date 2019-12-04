package com.huynguyen.videosharing.mapper.videos;

import com.huynguyen.videosharing.domain.dto.request.VideoSharingDTO;
import com.huynguyen.videosharing.domain.dto.response.VideoSharingResponseDTO;
import com.huynguyen.videosharing.domain.model.Video;
import com.huynguyen.videosharing.mapper.RequestMapper;
import com.huynguyen.videosharing.mapper.ResponseMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VideoMapper extends RequestMapper<VideoSharingDTO, Video>,
    ResponseMapper<Video, VideoSharingResponseDTO> {
  @Mappings({
      @Mapping(target = "user", ignore = true),
      @Mapping(target = "id", ignore = true)
  })
  Video transform(VideoSharingDTO request);

  @Override
  VideoSharingResponseDTO transform(Video domain);
}
