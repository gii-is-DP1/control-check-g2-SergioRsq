package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class FeedingController {
    
    private static final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";
    
    private FeedingService feedingService;
    
    private PetService petService;
    
    public FeedingController(@Autowired FeedingService feedingService, @Autowired PetService petService) {
        this.feedingService = feedingService;
        this.petService = petService;
    }
    
    @GetMapping("/feeding/create")
    public String initCreationForm(ModelMap modelMap) {
        Feeding feeding = new Feeding();
        modelMap.put("feeding", feeding);
        modelMap.put("feedingTypes", feedingService.getAllFeedingTypes());
        modelMap.put("pets", petService.getAllPets());
        
        return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/feeding/create")
    public String processCreationForm(@Valid Feeding feeding, BindingResult bindingResult) throws UnfeasibleFeedingException {
    
        FeedingType feedingType = feedingService.getFeedingType(feeding.getFeedingType().getName());
        Pet pet= petService.getPetByName(feeding.getPet().getName());
        
        if (!feedingType.getPetType().equals(pet.getType())) {
            bindingResult.rejectValue("petType", "error.feeding", "La mascota seleccionada no se le puede asignar el plan de \n" +
                    "alimentación especificado.");
        }
        
        if (bindingResult.hasErrors()) {
            return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
        } else {
            this.feedingService.save(feeding);
            return "redirect:/welcome";
        }
    }
    
}
