package com.huynguyen.videosharing.domain.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "videos")
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Getter
@NoArgsConstructor(force = true)
@EntityListeners(AuditingEntityListener.class)
public class Video extends Auditable<Long> {

  private static final long serialVersionUID = 5925925210655442883L;

  @Id
  @Setter
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_generator")
  @SequenceGenerator(name = "video_generator", sequenceName = "videos_id_seq", allocationSize = 1)
  private Long id;

  @Setter
  private String url;

  @Setter
  private String title;

  @Setter
  private String description;

  @Setter
  @Basic(fetch = FetchType.LAZY)
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
