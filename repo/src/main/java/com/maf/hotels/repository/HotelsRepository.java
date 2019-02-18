package com.maf.hotels.repository;


import com.maf.hotels.model.Hotels;
import com.maf.hotels.model.Request;

import java.util.List;

//interface for hotel repository that implemented by BestHottel repository , CrazyHotelRepo and MultiHotels
public interface HotelsRepository {
    List<Hotels> getHotelsData(Request request);
}
