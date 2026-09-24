package top.lll44556.auth.mapper;

import org.mapstruct.Mapper;
import top.lll44556.auth.BO.UserBO;
import top.lll44556.auth.entity.UserEntity;

/**
 * @author liululu
 * @date 2026/9/24 17:33
 * @description:
 */
@Mapper(componentModel = "spring")
public interface UserConvert {
    UserBO toBO(UserEntity user);

}
