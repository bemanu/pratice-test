package com.beb.practicetest.device;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class AuditListener {
    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyOperation(final Object object) {  }
}
