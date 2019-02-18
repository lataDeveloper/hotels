package com.maf.hotels.constant;

import com.maf.hotels.model.BestHotels;
import com.maf.hotels.model.CrazyHotels;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//this class used for get dummy data to api's
public class ConstantData {


    private static BestHotels bestHotels;
    private static CrazyHotels crazyHotels;
    private static List<BestHotels> bestHotelsList;
    private static List<CrazyHotels> crazyHotelsList;

    public static List<BestHotels> getBestHotelsData(){
        fillBestHotelsData();
        bestHotelsList = new ArrayList<>();
        bestHotelsList.add(bestHotels);
        return bestHotelsList;
    }

    public static List<CrazyHotels> getCrazyData(){
        fillCrazyHotelsData();
        crazyHotelsList = new ArrayList<>();
        crazyHotelsList.add(crazyHotels);
        return crazyHotelsList;
    }

    private static void fillBestHotelsData() {
        bestHotels = new BestHotels();
        bestHotels.setName("Sheraton");
        bestHotels.setPrice(new BigDecimal(100.00));
        bestHotels.setRate(5);
        bestHotels.setRoomAmenities("food,swimming");
    }
    private static void fillCrazyHotelsData() {
        crazyHotels = new CrazyHotels();
        List<String> amenities = new ArrayList<>();
        amenities.add("food");
        amenities.add("bar");
        crazyHotels.setName("Four Seasons");
        crazyHotels.setPrice(new BigDecimal(200.00));
        crazyHotels.setRate("****");
        crazyHotels.setAmenities(amenities);
        crazyHotels.setDiscount("15%");
    }
}
