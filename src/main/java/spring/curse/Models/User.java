package spring.curse.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username is empty")
    private String username;

    private String password;

    private boolean active;

    private Long countWaterCold;
    private Long countWaterHot;
    private Long countElectricity;
    private Long countHeating;
    private Long countTrash;
    private Long countWaterTrash;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    public User() {
    }

    public User(Long id, String username, String password, boolean active, Long countWaterCold, Long countWaterHot, Long countElectricity, Long countHeating, Long countTrash, Long countWaterTrash, Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.countWaterCold = countWaterCold;
        this.countWaterHot = countWaterHot;
        this.countElectricity = countElectricity;
        this.countHeating = countHeating;
        this.countTrash = countTrash;
        this.countWaterTrash = countWaterTrash;
        this.roles = roles;
    }

    public boolean isAdmin(){
        return roles.contains(Roles.ADMIN);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        return isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountWaterCold() {
        return countWaterCold;
    }

    public void setCountWaterCold(Long countWaterCold) {
        this.countWaterCold = countWaterCold;
    }

    public Long getCountWaterHot() {
        return countWaterHot;
    }

    public void setCountWaterHot(Long countWaterHot) {
        this.countWaterHot = countWaterHot;
    }

    public Long getCountElectricity() {
        return countElectricity;
    }

    public void setCountElectricity(Long countElectricity) {
        this.countElectricity = countElectricity;
    }

    public Long getCountHeating() {
        return countHeating;
    }

    public void setCountHeating(Long countHeating) {
        this.countHeating = countHeating;
    }

    public Long getCountTrash() {
        return countTrash;
    }

    public void setCountTrash(Long countTrash) {
        this.countTrash = countTrash;
    }

    public Long getCountWaterTrash() {
        return countWaterTrash;
    }

    public void setCountWaterTrash(Long countWaterTrash) {
        this.countWaterTrash = countWaterTrash;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
