package com.maf.hotels.repository;

import com.maf.hotels.model.Hotels;
import com.maf.hotels.model.Request;

import java.util.ArrayList;
import java.util.List;

//this class use to get data from multi api's (Best,Crazy)
public class MultipleHotelsProviderRepository implements HotelsRepository {

    private HotelsRepository bestHotelsRepository;
    private HotelsRepository crazyHotelsRepository;

    public MultipleHotelsProviderRepository(HotelsRepository bestHotelsRepository, HotelsRepository crazyHotelsRepository) {
        this.bestHotelsRepository = bestHotelsRepository;
        this.crazyHotelsRepository = crazyHotelsRepository;
    }

    @Override
    public List<Hotels> getHotelsData(Request request) {
        List<Hotels> allHotels = new ArrayList<>();
        allHotels.addAll(bestHotelsRepository.getHotelsData(request));
        allHotels.addAll(crazyHotelsRepository.getHotelsData(request));
        return allHotels;
    }
}
