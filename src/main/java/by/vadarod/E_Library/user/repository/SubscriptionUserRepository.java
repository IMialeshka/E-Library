package by.vadarod.E_Library.user.repository;

import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionUserRepository extends JpaRepository<SubscriptionUserEntity, Long> {

    public List<SubscriptionUserEntity> findBySubscriptionId(long id);

    public SubscriptionUserEntity findFirstByUserIdOrderByEndDateDesc(long id);

    public List<SubscriptionUserEntity> findByUserId(long id);
}
