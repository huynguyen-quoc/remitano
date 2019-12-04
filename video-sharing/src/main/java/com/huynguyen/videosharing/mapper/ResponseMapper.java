package com.huynguyen.videosharing.mapper;

import com.huynguyen.videosharing.domain.DomainObject;
import com.huynguyen.videosharing.domain.Response;

public interface ResponseMapper<M extends DomainObject, R extends Response> {

  R transform(M domain);
}
