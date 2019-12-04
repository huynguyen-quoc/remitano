package com.huynguyen.videosharing.domain;

public interface ValueObject extends DomainObject {

  boolean equals(Object obj);

  int hashCode();

}
