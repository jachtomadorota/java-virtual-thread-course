package com.vinsguru.sec09.security.controller;

import com.vinsguru.sec09.security.SecurityContext;
import com.vinsguru.sec09.security.UserRole;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class DocumentController {


    private final Supplier<SecurityContext> securityContextSupplier;

    public DocumentController(Supplier<SecurityContext> securityContextSupplier) {
        this.securityContextSupplier = securityContextSupplier;
    }

    public void read() {

    }

    private void validateUserRole(UserRole requiredRole) {
        var securityContext = this.securityContextSupplier.get();
        if(securityContext.hasPermission(requiredRole)) {
            log.error("User: {} has insufficient permissions", securityContext.userId());
            throw new SecurityException("Unauthorized access");
        }
    }
}
