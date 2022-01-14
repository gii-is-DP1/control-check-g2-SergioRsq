package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class FeedingService {
    
    private FeedingRepository feedingRepository;
    
    public FeedingService(@Autowired FeedingRepository feedingRepository) {
        this.feedingRepository = feedingRepository;
    }
    
    public List<Feeding> getAll() throws DataAccessException {
        return feedingRepository.findAll();
    }
    
    public List<FeedingType> getAllFeedingTypes() {
        return null;
    }
    
    public FeedingType getFeedingType(String typeName) throws DataAccessException {
        return feedingRepository.findFeedingTypeByName(typeName);
    }
    
    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(@Valid Feeding p) throws UnfeasibleFeedingException {
        
        FeedingType feedingType = p.getFeedingType();
        
        if (feedingType.getPetType().equals(p.getPet().getType())) {
            
            feedingRepository.save(p);
            
            return p;
        } else {
            throw new UnfeasibleFeedingException();
        }
    }
    
    
}
