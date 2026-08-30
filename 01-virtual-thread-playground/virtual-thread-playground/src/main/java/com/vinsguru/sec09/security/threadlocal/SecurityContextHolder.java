package com.vinsguru.sec09.security.threadlocal;

import com.vinsguru.sec09.security.SecurityContext;
import com.vinsguru.sec09.security.UserRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityContextHolder {

    private static final SecurityContext GUEST_CONTEXT = new SecurityContext(0, UserRole.GUEST);
    private static final ThreadLocal<SecurityContext> contextHolder = ThreadLocal.withInitial(() -> GUEST_CONTEXT);

    public SecurityContext getContext() {
        return contextHolder.get();
    }

    static void setContext(SecurityContext securityContext) {
        contextHolder.set(securityContext);
    }

    static void clear() {
        contextHolder.remove();
    }
}
