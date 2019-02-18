package com.maf.hotels;


import com.maf.hotels.repository.BestHotelsWsRepository;
import com.maf.hotels.repository.CrazyHotelsWsRepository;
import com.maf.hotels.repository.HotelsRepository;
import com.maf.hotels.repository.MultipleHotelsProviderRepository;
import com.maf.hotels.validator.RequestValidator;
import com.maf.hotels.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//this class is for configuration to create beans
@Configuration
public class HotelsCotrollerConfiguration {



    @Bean
    public HotelsRepository hotelsMultipleRepository(){

     //here we have two repository that call two api's and third repository to return data from two web services
        CrazyHotelsWsRepository crazyHotelsWsRepository = new CrazyHotelsWsRepository("http://localhost:8181/CrazyHotels");
        BestHotelsWsRepository bestHotelsWsRepository = new BestHotelsWsRepository("http://localhost:8181/BestHotels");
        return new MultipleHotelsProviderRepository(bestHotelsWsRepository,crazyHotelsWsRepository);
    }

    @Bean
    public Validator requestValidator(){
        return new RequestValidator();
    }
}
