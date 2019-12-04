package com.huynguyen.videosharing.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huynguyen.videosharing.domain.ValueObject;
import java.time.Instant;
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
public class Timestamp implements ValueObject {

  private static final long serialVersionUID = -4784878620303226347L;

  @JsonValue
  private Instant value;

  @JsonCreator
  public Timestamp(String value) {
    this.value = Instant.parse(value);
  }

}
