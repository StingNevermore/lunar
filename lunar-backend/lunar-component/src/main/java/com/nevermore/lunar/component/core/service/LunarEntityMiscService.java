package com.nevermore.lunar.component.core.service;

import com.nevermore.lunar.component.core.models.LunarEntity;
import com.nevermore.lunar.component.core.models.LunarEntitySchema;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

/**
 * @author nevermore
 */
public interface LunarEntityMiscService {
    @Nullable
    LunarEntity getEntityBy(String entityName);

    void insertEntity(String entityName, long creatorId, String desc);

    void createEntity(@Nonnull String entityName, long creatorId, @Nonnull String desc, @Nonnull Collection<LunarEntitySchema> schemas);

    @Nullable
    Map<String, LunarEntitySchema> getEntitySchemasBy(String entityName);

    void upsertSchema(@Nonnull Collection<LunarEntitySchema> schemas);
}
