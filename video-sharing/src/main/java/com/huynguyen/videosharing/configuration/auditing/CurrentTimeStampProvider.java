package com.huynguyen.videosharing.configuration.auditing;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

@Component("currentTimeStampProvider")
public class CurrentTimeStampProvider implements DateTimeProvider {

  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(Instant.now());
  }
}
