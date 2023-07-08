package com.hms.springbackendhms.util.Medicine;

import com.hms.springbackendhms.util.Prescription.Prescription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;

    @ManyToOne
    @JoinTable(name = "prescription_medicine",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private Prescription prescription;


    String name;
    String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
