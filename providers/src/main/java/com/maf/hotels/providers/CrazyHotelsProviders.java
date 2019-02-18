package com.maf.hotels.providers;

import com.maf.hotels.constant.ConstantData;
import com.maf.hotels.model.CrazyHotels;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Api of CrazuHotels that return json data
@RestController
public class CrazyHotelsProviders {

    @GetMapping(value = "/CrazyHotels",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CrazyHotels>> getCrazyHotels(@RequestParam String city, @RequestParam  int adultsCount, @RequestParam String from, @RequestParam String to){
        return ResponseEntity.ok().body(ConstantData.getCrazyData());

    }
}
