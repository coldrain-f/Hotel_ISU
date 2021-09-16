package edu.coldrain.hotel_isu.mapper.user;

import edu.coldrain.hotel_isu.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findByAll();

    User findByLoginId(String loginId);

    boolean save(User user);
}
