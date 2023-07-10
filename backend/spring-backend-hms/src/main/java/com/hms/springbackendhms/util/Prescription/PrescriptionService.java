package com.hms.springbackendhms.util.Prescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public void updatePrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public void addListPrescription(List<Prescription> prescriptionList) {
        for(int i=0; i< (long) prescriptionList.size();i++) {
            prescriptionRepository.save(prescriptionList.get(i));
        }
    }
}
