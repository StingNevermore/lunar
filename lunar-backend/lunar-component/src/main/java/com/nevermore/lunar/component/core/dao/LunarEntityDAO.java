package com.nevermore.lunar.component.core.dao;

import com.nevermore.lunar.component.core.models.LunarEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nevermore
 */
@Repository
public interface LunarEntityDAO {

    List<LunarEntity> selectByNames(List<String> entityNames);

    int insertEntity(LunarEntity entity);

}
