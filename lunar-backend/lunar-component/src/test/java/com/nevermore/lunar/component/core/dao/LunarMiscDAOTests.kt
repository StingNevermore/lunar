package com.nevermore.lunar.component.core.dao

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest
import com.nevermore.lunar.component.core.models.FieldType.LONG
import com.nevermore.lunar.component.core.models.JdbcType.BIGINT
import com.nevermore.lunar.component.core.models.LunarEntity
import com.nevermore.lunar.component.core.models.LunarEntitySchema
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrowsExactly
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.dao.DuplicateKeyException
import java.lang.System.currentTimeMillis
import java.util.*


/**
 * @author nevermore
 */
private const val DEPARTMENT = "department"
private const val WHATEVER = "whatever"
private const val CREATOR_ID = 1L

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = NONE)
class LunarEntityDAOTest(
    @Autowired private val entityDAO: LunarEntityDAO
) {

    @Test
    fun testSelectByNames() {
        val entities = entityDAO.selectByNames(listOf(DEPARTMENT))
        assertAll(
            Executable { assertEquals(1, entities.size) },
            Executable { assertNotNull(entities[0]) },
            Executable { assertEquals(1, entities[0].creatorId) },
        )
    }

    @Test
    fun insertEntity() {
        val result = entityDAO.insertEntity(
            LunarEntity(WHATEVER, currentTimeMillis(), CREATOR_ID, WHATEVER)
        )
        assertEquals(1, result)

        assertThrowsExactly(DuplicateKeyException::class.java) {
            entityDAO.insertEntity(
                LunarEntity(DEPARTMENT, currentTimeMillis(), CREATOR_ID, DEPARTMENT)
            )
        }

    }
}

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = NONE)
class LunarEntitySchemaDAOTest(
    @Autowired
    private val schemaDAO: LunarEntitySchemaDAO
) {

    @Test
    fun testSelectSchemaByName() {
        val lunarEntitySchemas = schemaDAO.selectByEntityName(DEPARTMENT)
        assertEquals(3, lunarEntitySchemas.size)
    }

    @Test
    fun testUpsert() {
        val schema = LunarEntitySchema(
            WHATEVER,
            WHATEVER,
            LONG,
            BIGINT,
            WHATEVER,
            WHATEVER,
            currentTimeMillis(),
            CREATOR_ID,
            WHATEVER
        )
        val result = schemaDAO.upsert(Collections.singleton(schema))
        assertEquals(1, result)

        val schemas = listOf(schema.copy(creatorId = 2L), schema.copy(entityName = DEPARTMENT))
        val result2 = schemaDAO.upsert(schemas)
        assertEquals(3, result2)

        val selectResult = schemaDAO.selectByEntityName(WHATEVER)
        assertEquals(1, selectResult.size)
        assertEquals(2L, selectResult[0].creatorId)
    }
}