package com.hms.springbackendhms.util.MedicalAction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalActionRepository extends JpaRepository<MedicalAction,Integer> {
}
