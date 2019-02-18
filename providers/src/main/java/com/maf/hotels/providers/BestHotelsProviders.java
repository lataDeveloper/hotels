package com.maf.hotels.providers;

import com.maf.hotels.constant.ConstantData;
import com.maf.hotels.model.BestHotels;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Api of BestHotels that return json data
@RestController
public class BestHotelsProviders {

    @ResponseBody
    @GetMapping(value = "/BestHotels",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BestHotels>> getBestHotels(@RequestParam String city, @RequestParam  int numOfAdults, @RequestParam String fromDate, @RequestParam String toDate){
        return ResponseEntity.ok().body(ConstantData.getBestHotelsData());
    }

}
