package com.huynguyen.videosharing.mapper;

import com.huynguyen.videosharing.domain.DomainObject;
import com.huynguyen.videosharing.domain.Request;

public interface RequestMapper<R extends Request, M extends DomainObject> {

  M transform(R request);
}
