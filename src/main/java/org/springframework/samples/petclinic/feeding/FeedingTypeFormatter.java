package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType> {
    
    private FeedingService feedingService;
    
    public FeedingTypeFormatter(@Autowired FeedingService feedingService) {
        this.feedingService = feedingService;
    }
    
    @Override
    public String print(@Valid FeedingType feedingType, Locale locale) {
        return feedingType.getName();
    }
    
    @Override
    public FeedingType parse(String typeName, Locale locale) throws ParseException {
        return Optional.ofNullable(feedingService.getFeedingType(typeName)).orElseThrow(()->new ParseException("Such feeding type is not " +
                "registered in the database", 0));
    }
    
}
