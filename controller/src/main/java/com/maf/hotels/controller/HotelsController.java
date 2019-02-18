package com.maf.hotels.controller;

import com.maf.hotels.model.Hotels;
import com.maf.hotels.model.Request;
import com.maf.hotels.repository.HotelsRepository;
import com.maf.hotels.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class HotelsController {

    //here define multiRepository to use it in getAvailableHotels method
    @Autowired
    private HotelsRepository hotelsMultipleRepository;

    //define validator that validate Request
    @Autowired
    private Validator requestValidator;

    //this post method to call apis and get data from Api's
    @PostMapping(value = "/hotels",consumes="application/json")
    public ResponseEntity<List<Hotels>> getAvailableHotels(@RequestBody Request request){
        requestValidator.validate(request);
        List<Hotels> hotels = hotelsMultipleRepository.getHotelsData(request);
        hotels.sort((o1, o2) -> o2.getRate()-o1.getRate());
        return ResponseEntity.ok(hotels);
    }
}
