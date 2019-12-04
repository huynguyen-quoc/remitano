package com.huynguyen.videosharing.domain.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.huynguyen.videosharing.domain.Response;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PageDataResponse<T extends Serializable> implements Response {

  private static final long serialVersionUID = 155405991017952320L;

  private List<T> data;
  private PageResponse page;

}
