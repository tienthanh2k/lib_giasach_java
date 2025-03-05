package com.thanhnt10.dodobook.common.listener;

import com.thanhnt10.dodobook.common.business.TenantAware;
import com.thanhnt10.dodobook.common.util.Snowflake;
import jakarta.persistence.PrePersist;

public class GenerateEntityIdListener {
    @PrePersist
    public void setId(Object entity) {
        final Long snowflakeId = Snowflake.getInstance().nextId();
        ((TenantAware) entity).setId(snowflakeId);
    }
}
