package com.huynguyen.videosharing.domain.model;

import com.huynguyen.videosharing.domain.IdentifiableDomainObject;
import com.huynguyen.videosharing.domain.valueobject.Timestamp;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter
@NoArgsConstructor
public abstract class Auditable<T extends Serializable> implements IdentifiableDomainObject<T> {

  private static final long serialVersionUID = 2014271243101975136L;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "created_at", updatable = false))
  @CreatedDate
  protected Timestamp createdAt;

  @Embedded
  @LastModifiedDate
  @AttributeOverride(name = "value", column = @Column(name = "last_modified_at", updatable = false))
  protected Timestamp lastModifiedAt;
}
