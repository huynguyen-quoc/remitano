package com.huynguyen.videosharing.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonValue;
import com.huynguyen.videosharing.domain.ValueObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Embeddable
public class DateTime implements ValueObject {

  private static final long serialVersionUID = -1957743697798068486L;
  @JsonValue
  private LocalDateTime value;

  public static DateTime now() {
    return DateTime.builder().value(LocalDateTime.now()).build();
  }
}
