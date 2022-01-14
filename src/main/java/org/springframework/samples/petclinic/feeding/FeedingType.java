package org.springframework.samples.petclinic.feeding;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="feeding_type")
public class FeedingType extends BaseEntity {
    
    
    @NotEmpty
    @Length(min = 5, max = 30)
    @Column(nullable = false, unique = true)
    private String name;
    
    @NotBlank
    private String description;
    
    
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_type_id", nullable = false)
    private PetType petType;
}
