package com.security.demo.model.enums;

import com.security.demo.model.permissions.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    ADMIN(Set.of(Permission.DEVELOPERS_WRITE, Permission.DEVELOPERS_READ)),
    USER(Set.of(Permission.DEVELOPERS_READ));

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
    }

}
