package top.lll44556.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.lll44556.auth.entity.UserEntity;

import java.util.Optional;

/**
 * @author liululu
 * @date 2026/9/24 16:45
 * @description:
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByIdAndValid(String id, int valid);
}
