package com.nevermore.lunar.framework.configs.mock.dao;

import com.nevermore.lunar.framework.configs.mock.Lunar;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nevermore
 */
@Repository
public interface MockTestDAO {

    List<Lunar> queryAll();

    Lunar queryById(long id);
}
