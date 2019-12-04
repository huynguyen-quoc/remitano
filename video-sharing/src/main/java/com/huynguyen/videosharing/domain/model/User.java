package com.huynguyen.videosharing.domain.model;


import com.huynguyen.videosharing.domain.enums.UserStatus;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Getter
@NoArgsConstructor(force = true)
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable<Long> implements UserDetails {

  private static final long serialVersionUID = 304422261498869882L;
  @Id
  @Setter
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
  @SequenceGenerator(name = "user_generator", sequenceName = "users_id_seq", allocationSize = 1)
  private Long id;

  @Setter
  private String username;

  @Setter
  private String password;

  @Setter
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
