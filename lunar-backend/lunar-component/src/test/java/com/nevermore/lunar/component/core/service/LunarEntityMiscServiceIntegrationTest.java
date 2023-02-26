package com.nevermore.lunar.component.core.service;

import com.nevermore.lunar.component.core.models.LunarEntity;
import com.nevermore.lunar.component.core.models.LunarEntitySchema;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.nevermore.lunar.component.core.models.FieldType.LONG;
import static com.nevermore.lunar.component.core.models.JdbcType.BIGINT;
import static com.nevermore.lunar.framework.utils.TestSuiteConstantsKt.INTEGRATION_TEST;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author nevermore
 */
@SpringBootTest
@Tag(INTEGRATION_TEST)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LunarEntityMiscServiceIntegrationTest {

    private static final String DEPARTMENT = "department";
    private static final String WHATEVER = "whatever";

    @Autowired
    private LunarEntityMiscService miscService;

    @Test
    @Order(0)
    public void testGetEntityBy() {
        LunarEntity entity = miscService.getEntityBy(DEPARTMENT);
        assertNotNull(entity);
    }

    @Test
    @Order(1)
    public void testGetEntitySchemaBy() {
        var schemas = miscService.getEntitySchemasBy(DEPARTMENT);
        assertNotNull(schemas);
        assertEquals(3, schemas.size());
    }

    @Test
    @Order(2)
    public void testUpsertSchema() {
        var schema = new LunarEntitySchema(
                DEPARTMENT, DEPARTMENT, LONG, BIGINT,
                DEPARTMENT, DEPARTMENT, currentTimeMillis(),
                1L, DEPARTMENT
        );
        miscService.upsertSchema(singleton(schema));
        var result = miscService.getEntitySchemasBy(DEPARTMENT);
        assertNotNull(result);
        assertEquals(1L, result.get(DEPARTMENT).getCreatorId());

        List<LunarEntitySchema> schemas = new ArrayList<>();
        schemas.add(new LunarEntitySchema(
                DEPARTMENT, DEPARTMENT, LONG, BIGINT,
                DEPARTMENT, DEPARTMENT, currentTimeMillis(),
                2L, DEPARTMENT
        ));
        miscService.upsertSchema(schemas);

        schemas = new ArrayList<>();
        schemas.add(new LunarEntitySchema(
                WHATEVER, WHATEVER, LONG, BIGINT,
                WHATEVER, WHATEVER, currentTimeMillis(),
                1L, WHATEVER
        ));
        schemas.add(new LunarEntitySchema(
                WHATEVER, WHATEVER + "1", LONG, BIGINT,
                WHATEVER, WHATEVER, currentTimeMillis(),
                1L, WHATEVER
        ));
        miscService.upsertSchema(schemas);

        var departmentSchemas = miscService.getEntitySchemasBy(DEPARTMENT);
        var whateverSchemas = miscService.getEntitySchemasBy(WHATEVER);
        assertNotNull(departmentSchemas);
        assertNotNull(whateverSchemas);
        assertEquals(2, whateverSchemas.size());
        assertEquals(2L, departmentSchemas.get(DEPARTMENT).getCreatorId());
    }

}
