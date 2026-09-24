package top.lll44556.auth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.lll44556.auth.BO.UserBO;
import top.lll44556.auth.entity.UserEntity;
import top.lll44556.auth.mapper.UserConvert;
import top.lll44556.auth.repository.UserRepository;
import top.lll44556.auth.service.UserService;

import java.util.Optional;

/**
 * @author liululu
 * @date 2026/9/24 16:44
 * @description:
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConvert userConvert;

    @Override
    public UserBO findById(String id) {
        Optional<UserEntity> entity = userRepository.findByIdAndValid(id, 1);
        return entity.map(userConvert::toBO).orElse(null);
    }
}
