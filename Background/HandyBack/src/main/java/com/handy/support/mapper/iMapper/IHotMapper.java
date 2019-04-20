package com.handy.support.mapper.iMapper;

import org.apache.ibatis.annotations.Param;
import com.handy.support.entity.Hot;
import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public interface IHotMapper {
    List<Hot> listMax(@Param("n") Integer n);

}
