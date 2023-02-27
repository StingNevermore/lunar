package com.nevermore.lunar.server;

import com.nevermore.lunar.component.core.models.LunarEntitySchema;
import com.nevermore.lunar.component.core.service.LunarEntityMiscService;
import com.nevermore.lunar.server.vo.CreateEntityVo;
import com.nevermore.lunar.server.vo.LunarApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nevermore.lunar.server.vo.ResultUtils.badRequestResult;
import static com.nevermore.lunar.server.vo.ResultUtils.successResult;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.singleton;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author nevermore
 */
@Slf4j
@RestController
@RequestMapping("/api/lunar")
public class LunarManageController {

    private final LunarEntityMiscService entityMiscService;

    public LunarManageController(LunarEntityMiscService entityMiscService) {
        this.entityMiscService = entityMiscService;
    }

    @PostMapping("/create")
    public ResponseEntity<LunarApiResult> createEntity(@RequestBody @Validated CreateEntityVo vo) {
        try {
            LunarEntitySchema schema = new LunarEntitySchema(
                    vo.entityName(), vo.fieldName(),
                    vo.fieldType(), vo.jdbcType(), vo.defaultValue(),
                    vo.placeHolder(), currentTimeMillis(),
                    1L, vo.schemaDesc()
            );
            entityMiscService.createEntity(vo.entityName(), 1L, vo.entityDesc(), singleton(schema));
            return ok(successResult());
        } catch (IllegalArgumentException e) {
            log.error("bad request, ", e);
            return badRequest().body(badRequestResult());
        }
    }
}
