package com.nevermore.lunar.component.core.service.impl

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.nevermore.lunar.component.core.dao.LunarEntityDAO
import com.nevermore.lunar.component.core.dao.LunarEntitySchemaDAO
import com.nevermore.lunar.component.core.models.LunarEntity
import com.nevermore.lunar.component.core.models.LunarEntitySchema
import com.nevermore.lunar.component.core.service.LunarEntityMiscService
import com.nevermore.lunar.framework.utils.GuavaCacheUtils.safeGet
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author nevermore
 */
typealias EntitySchemaLine = Map<String, LunarEntitySchema>

@Lazy
@Slf4j
@Service
class LunarEntityMiscServiceImpl(
    private val entityDAO: LunarEntityDAO,
    private val schemaDAO: LunarEntitySchemaDAO,
) : LunarEntityMiscService {

    private val entityCache: LoadingCache<String, LunarEntity>
    private val schemaCache: LoadingCache<String, EntitySchemaLine>

    init {
        entityCache = CacheBuilder.newBuilder().build(loadingEntity())
        schemaCache = CacheBuilder.newBuilder().build(loadingEntitySchema())
    }

    override fun getEntityBy(entityName: String): LunarEntity? =
        safeGet(entityName) { key -> entityCache[key] }

    override fun getEntitySchemasBy(entityName: String): EntitySchemaLine? =
        safeGet(entityName) { key -> schemaCache[key] }

    override fun insertEntity(entityName: String, creatorId: Long, desc: String) {
        entityDAO.insertEntity(
            LunarEntity(entityName, System.currentTimeMillis(), creatorId, desc)
        )
    }

    @Transactional
    override fun createEntity(
        entityName: String,
        creatorId: Long,
        desc: String,
        schemas: Collection<LunarEntitySchema>
    ) {
        insertEntity(entityName, creatorId, desc)
        upsertSchema(schemas)
    }

    @Transactional
    override fun upsertSchema(schemas: Collection<LunarEntitySchema>) {
        require(schemas.isNotEmpty()) { "empty schemas" }
        val entityName = schemas.first().entityName
        require(schemas.all { it.entityName == entityName }) { "multiple entityName" }

        schemaDAO.upsert(schemas)
        schemaCache.refresh(entityName)
    }

    private fun loadingEntity() = object : CacheLoader<String, LunarEntity>() {
        override fun load(key: String): LunarEntity {
            val entities = entityDAO.selectByNames(listOf(key))
            require(entities!!.isNotEmpty()) { "Unable to load: $key" }
            return entities[0]
        }
    }

    private fun loadingEntitySchema() = object : CacheLoader<String, EntitySchemaLine>() {
        override fun load(key: String): EntitySchemaLine {
            val schemaList = schemaDAO.selectByEntityName(key)
            require(schemaList!!.isNotEmpty()) { "Unable to load $key" }
            return schemaList.associateBy { it.fieldName }
        }
    }
}


