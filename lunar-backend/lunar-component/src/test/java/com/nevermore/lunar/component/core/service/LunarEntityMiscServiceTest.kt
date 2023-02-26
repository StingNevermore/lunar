package com.nevermore.lunar.component.core.service

import com.nevermore.lunar.component.core.dao.LunarEntityDAO
import com.nevermore.lunar.component.core.dao.LunarEntitySchemaDAO
import com.nevermore.lunar.component.core.models.FieldType.INT
import com.nevermore.lunar.component.core.models.FieldType.LONG
import com.nevermore.lunar.component.core.models.JdbcType
import com.nevermore.lunar.component.core.models.JdbcType.BIGINT
import com.nevermore.lunar.component.core.models.LunarEntity
import com.nevermore.lunar.component.core.models.LunarEntitySchema
import com.nevermore.lunar.component.core.service.impl.LunarEntityMiscServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.any

/**
 * @author nevermore
 */
private const val DEPARTMENT = "department"
private const val CREATOR_ID = 1L
private const val DESC = "desc"
private const val WHATEVER = "whatever"

class LunarEntityMiscServiceTest {
    private val schemaService: LunarEntityMiscService
    private val entityDAO: LunarEntityDAO = Mockito.mock(LunarEntityDAO::class.java)
    private val schemaDAO: LunarEntitySchemaDAO = Mockito.mock(LunarEntitySchemaDAO::class.java)

    init {
        val entity = LunarEntity(DEPARTMENT, System.currentTimeMillis(), CREATOR_ID, DESC)
        Mockito.`when`(entityDAO.selectByNames(listOf(DEPARTMENT))).thenReturn(listOf(entity))
        Mockito.`when`(entityDAO.insertEntity(ArgumentMatchers.any())).thenReturn(1)
        val schemas: MutableList<LunarEntitySchema> = ArrayList(5)
        for (i in 0..4) {
            val mock = LunarEntitySchema(
                DEPARTMENT, "filedName$i", INT, JdbcType.INT,
                "default$i", "placeHolder$i",
                System.currentTimeMillis(), 1L, DESC + i
            )
            schemas.add(mock)
        }
        Mockito.`when`(schemaDAO.selectByEntityName(DEPARTMENT)).thenReturn(schemas)
        Mockito.`when`(schemaDAO.upsert(any())).thenReturn(1)
        schemaService = LunarEntityMiscServiceImpl(entityDAO, schemaDAO)
    }

    @Test
    fun testGetByName() {
        val department = schemaService.getEntityBy(DEPARTMENT)
        Assertions.assertNotNull(department)
        Mockito.verify(entityDAO).selectByNames(ArgumentMatchers.eq(listOf(DEPARTMENT)))
        val whatever = schemaService.getEntityBy(WHATEVER)
        Assertions.assertNull(whatever)
        Mockito.verify(entityDAO).selectByNames(ArgumentMatchers.eq(listOf(WHATEVER)))
    }

    @Test
    fun testInsertEntity() {
        schemaService.insertEntity("hr", 1L, "hr")
        Mockito.verify(entityDAO).insertEntity(ArgumentMatchers.any())
    }

    @Test
    fun testGetEntitySchemasBy() {
        val results = schemaService.getEntitySchemasBy(DEPARTMENT)
        Assertions.assertNotNull(results)
        Assertions.assertEquals(5, results!!.size)
        Mockito.verify(schemaDAO).selectByEntityName(ArgumentMatchers.eq(DEPARTMENT))
        val emptyResults = schemaService.getEntitySchemasBy(WHATEVER)
        Assertions.assertNull(emptyResults)
        Mockito.verify(schemaDAO).selectByEntityName(ArgumentMatchers.eq(WHATEVER))
    }

    @Test
    fun testUpsertSchema() {
        val schema = LunarEntitySchema(
            WHATEVER,
            WHATEVER, LONG, BIGINT,
            WHATEVER,
            WHATEVER,
            System.currentTimeMillis(),
            1L,
            WHATEVER
        )
        schemaDAO.upsert(setOf(schema))

        val schemas = setOf(schema.copy(entityName = WHATEVER), schema.copy(creatorId = 2L))
        schemaDAO.upsert(schemas)
    }
}