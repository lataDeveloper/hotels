package com.maf.hotels.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.maf.hotels.model.BestHotels;
import com.maf.hotels.model.Hotels;
import com.maf.hotels.model.Request;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Best hotels Api request start from here that call get request to best hotels to get response from api
public class BestHotelsWsRepository implements HotelsRepository {
    private String bestHotelsProviderUrl;
    private Gson gson;

    public BestHotelsWsRepository(String bestHotelsProviderUrl) {
        this.bestHotelsProviderUrl = bestHotelsProviderUrl;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public List<Hotels> getHotelsData(Request request) {
        return getBestHotelsData(request);
    }

    private List<Hotels> getBestHotelsData(Request request) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.bestHotelsProviderUrl)
                .queryParam("city", request.getCity())
                .queryParam("fromDate", request.getFromDate())
                .queryParam("toDate", request.getToDate())
                .queryParam("numOfAdults", request.getNumOfAdults());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, String.class);
        String body = exchange.getBody();
        Type listType = new TypeToken<ArrayList<BestHotels>>() {
        }.getType();
        List<BestHotels> bestHotelsList = gson.fromJson(body, listType);
        return bestHotelsList.stream().map(this::mapBestHotelToNormalHotel).collect(Collectors.toList());
    }

    private Hotels mapBestHotelToNormalHotel(BestHotels bestHotels) {
        Hotels hotels = new Hotels();

        hotels.setProviders("BestHotels");
        hotels.setAmenities(Arrays.asList(bestHotels.getRoomAmenities().split(",")));
        hotels.setFare(bestHotels.getPrice());
        hotels.setHotelName(bestHotels.getName());
        hotels.setRate(bestHotels.getRate());
        return hotels;
    }
}
