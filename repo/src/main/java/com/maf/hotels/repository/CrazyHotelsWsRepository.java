package com.maf.hotels.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.maf.hotels.model.CrazyHotels;
import com.maf.hotels.model.Hotels;
import com.maf.hotels.model.Request;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Crazy hotels Api request start from here that call get request to best hotels to get response from api
public class CrazyHotelsWsRepository implements HotelsRepository {
    private String crazyHotelProviderUrl;

    private Gson gson;

    private static final Map<String,Integer> ratingMap;

    static {
        ratingMap = new HashMap<>();

        ratingMap.put("*",1);
        ratingMap.put("**",2);
        ratingMap.put("***",3);
        ratingMap.put("****",4);
        ratingMap.put("*****",5);
    }

    public CrazyHotelsWsRepository(String crazyHotelProviderUrl) {
        this.crazyHotelProviderUrl = crazyHotelProviderUrl;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public List<Hotels> getHotelsData(Request request) {

        return getCrazyHotelsData(request);
    }

    private List<Hotels> getCrazyHotelsData(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.crazyHotelProviderUrl)
                .queryParam("city", request.getCity())
                //this date is ISO_INSTANT
                .queryParam("from", LocalDate.parse(request.getFromDate()).atStartOfDay().toString())
                .queryParam("to",LocalDate.parse(request.getToDate()).atStartOfDay().toString())
                .queryParam("adultsCount",request.getNumOfAdults());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, String.class);
        String responseBody = exchange.getBody();
        Type listType = new TypeToken<ArrayList<CrazyHotels>>() {}.getType();
        List<CrazyHotels> crazyHotels = gson.fromJson(responseBody,listType);

        return crazyHotels.stream()
                .map(this::mapCrazyHotelToNormalHotel)
                .collect(Collectors.toList());
    }

    private Hotels mapCrazyHotelToNormalHotel(CrazyHotels crazyHotels){
        Hotels hotels = new Hotels();

        hotels.setProviders("CrazyHotels");
        hotels.setAmenities(crazyHotels.getAmenities());
        hotels.setFare(crazyHotels.getPrice());
        hotels.setHotelName(crazyHotels.getName());
        hotels.setRate(ratingMap.get(crazyHotels.getRate()));
        return hotels;
    }

}
