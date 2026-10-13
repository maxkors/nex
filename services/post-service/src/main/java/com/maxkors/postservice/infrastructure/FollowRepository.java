package com.maxkors.postservice.infrastructure;

import com.maxkors.postservice.domain.Follow;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    long deleteByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    @Query(value = "select f.followerId from Follow f where f.followeeId = :followeeId")
    List<Long> findByFolloweeId(@Param("followeeId") Long followeeId);
}
