package com.fich.sarh.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtils {

    private SecurityUtils() {}

    public static String getCurrentUsername() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        // 👤 UserDetails o CustomUserDetails
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }

        // 👤 Principal como String (JWT simple)
        if (principal instanceof String s &&
                !"anonymousUser".equalsIgnoreCase(s)) {
            return s;
        }

        return null;
    }
}