package com.hms.springbackendhms.util.MedicalAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalActionService {
    private final MedicalActionRepository medicalActionRepository;

    @Autowired
    public MedicalActionService(MedicalActionRepository medicalActionRepository) {
        this.medicalActionRepository = medicalActionRepository;
    }

    public void addMedicalAction(MedicalAction medicalAction) {
        medicalActionRepository.save(medicalAction);
    }
}
