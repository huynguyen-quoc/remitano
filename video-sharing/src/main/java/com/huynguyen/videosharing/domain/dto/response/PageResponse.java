package com.huynguyen.videosharing.domain.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.huynguyen.videosharing.domain.Response;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Getter
@Builder
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PageResponse implements Response {

  private static final long serialVersionUID = 5879779816504831440L;

  private Integer totalPages;
  private Boolean hasNext;
  private Boolean hasPrevious;
  private Integer currentPage;
  private Long totalElements;

}
