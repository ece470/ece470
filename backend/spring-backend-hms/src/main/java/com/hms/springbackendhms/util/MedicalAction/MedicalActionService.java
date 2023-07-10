package com.hms.springbackendhms.util.MedicalAction;

import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addListMedicalAction(List<MedicalAction> medicalActionList) {
        for(int i=0; i< (long) medicalActionList.size();i++) {
            medicalActionRepository.save(medicalActionList.get(i));
        }
    }
}
