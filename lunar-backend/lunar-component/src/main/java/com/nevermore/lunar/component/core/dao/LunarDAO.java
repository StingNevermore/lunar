package com.nevermore.lunar.component.core.dao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

/**
 * @author nevermore
 */
@Repository
public interface LunarDAO {

    Map<String, Object> selectOne(Serializable id);

}
