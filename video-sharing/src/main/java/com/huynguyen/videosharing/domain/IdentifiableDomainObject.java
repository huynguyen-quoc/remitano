package com.huynguyen.videosharing.domain;

import java.io.Serializable;

public interface IdentifiableDomainObject<T extends Serializable> extends DomainObject {

  T getId();
}
