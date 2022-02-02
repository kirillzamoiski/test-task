package com.solbeg.taskbackend.repository;

import com.solbeg.taskbackend.model.ProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileInfoRepository extends JpaRepository<ProfileInfo, Long> {
}
