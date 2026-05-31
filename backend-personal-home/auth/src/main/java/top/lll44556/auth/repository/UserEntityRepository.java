package top.lll44556.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.lll44556.auth.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

    UserEntity findFirstBy();
}