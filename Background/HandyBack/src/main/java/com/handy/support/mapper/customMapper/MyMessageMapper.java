package com.handy.support.mapper.customMapper;

import com.handy.support.pojo.Message.dto.FavorDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyMessageMapper {
    List<FavorDTO> getFavorMessage(@Param("userId") int userId, @Param("startRow")  int startRow, @Param("size") int size);
}
