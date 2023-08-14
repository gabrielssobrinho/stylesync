package com.br.stylesync.model;

import com.br.stylesync.dto.UpdateEmployeeDto;
import com.br.stylesync.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AuditEntity implements UserDetails {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    @OneToOne
    private Image profileImage;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
        return this.active;
    }

    public static Employee currentUser() {
        return (Employee) RequestContextHolder.getRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_REQUEST);
    }

    public static void setCurrentUser(final Employee user) {
        RequestContextHolder.getRequestAttributes().setAttribute("user", user, RequestAttributes.SCOPE_REQUEST);
    }

    public static void deleteCurrentUser() {
        RequestContextHolder.getRequestAttributes().removeAttribute("user", RequestAttributes.SCOPE_REQUEST);
    }

    public void updateUser(UpdateEmployeeDto employeeRequest) {
        if(employeeRequest.name() != null){
            this.name = employeeRequest.name();
        }
        if(employeeRequest.email() != null){
            this.email = employeeRequest.email();
        }
        if(employeeRequest.phone() != null){
            this.phone = employeeRequest.phone();
        }
        if(employeeRequest.address() != null){
            this.address = employeeRequest.address();
        }
        if(employeeRequest.birthDate() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                this.birthDate = sdf.parse(employeeRequest.birthDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
