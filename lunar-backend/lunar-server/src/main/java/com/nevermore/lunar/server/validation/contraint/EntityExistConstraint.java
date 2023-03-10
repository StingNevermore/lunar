package com.nevermore.lunar.server.validation.contraint;

import com.nevermore.lunar.component.core.service.LunarEntityMiscService;
import com.nevermore.lunar.server.validation.validators.EntityExists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author nevermore
 */
public class EntityExistConstraint implements ConstraintValidator<EntityExists, String> {

    @Autowired
    private LunarEntityMiscService entityMiscService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return entityMiscService.getEntityBy(value) != null;
    }
}
