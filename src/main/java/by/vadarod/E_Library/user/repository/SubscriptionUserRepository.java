package by.vadarod.E_Library.user.repository;

import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SubscriptionUserRepository extends JpaRepository<SubscriptionUserEntity, Long> {

    public List<SubscriptionUserEntity> findBySubscriptionId(long id);

    public Optional<SubscriptionUserEntity> findFirstByUserIdOrderByEndDateDesc(long id);

    public List<SubscriptionUserEntity> findByUserId(long id);

    @Query("select s from SubscriptionUserEntity s where s.user = :user and s.startDate <= :dateS and s.endDate >= :dateE")
    public Optional<SubscriptionUserEntity> findActiveSubscriptionOfUser(@Param("user")UserEntity user, @Param("dateS") Date dateS, @Param("dateE") Date dateE);


}
