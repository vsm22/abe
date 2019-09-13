package anvil.security.entities.user.entity;

import anvil.domain.model.entity.UserInfo;
import anvil.domain.model.entity.crud.UserInfoCrudRepo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.NonFinal;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Builder
@Value
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "is_guest")
    boolean isGuest;

    @Column(name = "last_active")
    @NonFinal
    @Setter(AccessLevel.PUBLIC)
    LocalDateTime lastActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    UserInfo userInfo;

    @JsonCreator
    public User(@JsonProperty("id") final Long id,
                @JsonProperty("username") final String username,
                @JsonProperty("email") final String email,
                @JsonProperty("password") final String password,
                @JsonProperty("isGuest") final boolean isGuest,
                @JsonProperty("lastActive") final LocalDateTime lastActive,
                @JsonProperty("userInfo") final UserInfo userInfo) {

        super();

        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isGuest = isGuest;
        this.lastActive = LocalDateTime.now();
        this.userInfo = userInfo;
    }

    @JsonCreator
    public User(@JsonProperty("id") final Long id,
                @JsonProperty("username") final String username,
                @JsonProperty("email") final String email,
                @JsonProperty("password") final String password,
                @JsonProperty("lastActive") final LocalDateTime lastActive) {

        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isGuest = false;
        this.lastActive = LocalDateTime.now();
        this.userInfo = UserInfo.builder().build();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public String getEmail() { return email; }

    @JsonIgnore
    public Long getId() { return id; }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
