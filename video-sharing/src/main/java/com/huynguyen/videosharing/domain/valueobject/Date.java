package com.huynguyen.videosharing.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonValue;
import com.huynguyen.videosharing.domain.ValueObject;
import java.time.LocalDate;
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
public class Date implements ValueObject {

  private static final long serialVersionUID = -1957743697798068486L;
  @JsonValue
  private LocalDate value;

  public static Date now() {
    return Date.builder().value(LocalDate.now()).build();
  }
}
