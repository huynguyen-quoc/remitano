package com.huynguyen.videosharing.repository;

import com.huynguyen.videosharing.domain.model.Video;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VideoRepository extends PagingAndSortingRepository<Video, Long> {

}
