package com.example.blogapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id")
    )
    private List<Role> roles=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.getName();
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
