package org.springframework.samples.petclinic.feeding;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Feeding extends BaseEntity {
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name="start_date")
    private LocalDate startDate;
    
    
    @Positive
    @Column(name = "weeks_duration", nullable = false)
    private double weeksDuration;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
    
    @ManyToOne
    @JoinColumn(name = "feeding_type_id")
    private FeedingType feedingType;
}
