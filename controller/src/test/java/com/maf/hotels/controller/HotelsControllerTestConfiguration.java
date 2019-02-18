package com.maf.hotels.controller;

import com.maf.hotels.model.Hotels;
import com.maf.hotels.model.Request;
import com.maf.hotels.repository.HotelsRepository;
import com.maf.hotels.validator.RequestValidator;
import com.maf.hotels.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class HotelsControllerTestConfiguration {

    @Bean
    public HotelsController hotelsControllerMock(){
        return new HotelsController();
    }

    @Bean
    public HotelsRepository mockRepo(){
        return new HotelsRepository() {
            @Override
            public List<Hotels> getHotelsData(Request request) {


                Hotels fiveStarsHotel = new Hotels();
                fiveStarsHotel.setHotelName("5_STARTS_HOTEL");
                fiveStarsHotel.setFare(new BigDecimal(300));
                fiveStarsHotel.setAmenities(new ArrayList<>());
                fiveStarsHotel.setProviders("BestHotels");
                fiveStarsHotel.setRate(5);

                Hotels threeStarsHotel = new Hotels();
                threeStarsHotel.setHotelName("3_STARTS_HOTEL");
                threeStarsHotel.setFare(new BigDecimal(300));
                threeStarsHotel.setAmenities(new ArrayList<>());
                threeStarsHotel.setProviders("BestHotels");
                threeStarsHotel.setRate(3);
                return Arrays.asList(threeStarsHotel,fiveStarsHotel);
            }
        };
    }

    @Bean
    public Validator validator(){
        return new RequestValidator();
    }

}
