package com.huynguyen.videosharing.domain.valueobject;

import com.huynguyen.videosharing.domain.ValueObject;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class Time implements ValueObject {

  private static final long serialVersionUID = 3409613252249075876L;

  private LocalTime value;

  public static Time now() {
    return Time.builder().value(LocalTime.now()).build();
  }
}
