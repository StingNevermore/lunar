package com.nevermore.lunar.component.core.dao;

import com.nevermore.lunar.component.core.models.LunarEntitySchema;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author nevermore
 */
@Repository
public interface LunarEntitySchemaDAO {

    List<LunarEntitySchema> selectByEntityName(String entityName);

    int upsert(Collection<LunarEntitySchema> entitySchemas);
}
