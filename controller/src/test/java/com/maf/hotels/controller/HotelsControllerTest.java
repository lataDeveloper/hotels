package com.maf.hotels.controller;

import com.maf.hotels.model.Hotels;
import com.maf.hotels.model.Request;
import com.maf.hotels.validator.RequestValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HotelsControllerTestConfiguration.class)
public class HotelsControllerTest {

    @Autowired
    private HotelsController hotelsControllerMock;

    private  ResponseEntity<List<Hotels>> availableHotelsResponse;

    @Before
    public void setup(){
        Request request = new Request();
        request.setCity("AUH");
        request.setFromDate("2019-05-02");
        request.setToDate("2019-05-06");
        request.setNumOfAdults(5);
        availableHotelsResponse = hotelsControllerMock.getAvailableHotels(request);
    }

    @Test
    public void givenTrueRequest_WhenCallRepository_thenReturnStatusCode200() {
        HttpStatus statusCode = availableHotelsResponse.getStatusCode();
        Assert.assertEquals(200,statusCode.value());
    }

    @Test
    public void givenTrueRequest_WhenCallMockRepository_ThenReturnTwoItem() {
        List<Hotels> hotels = availableHotelsResponse.getBody();
        Assert.assertEquals(2,hotels.size());
    }

    @Test
    public void givenTrueRequest_WhenCallMockRepository_ThenReturnOrderedItemsBasedRate() {
        List<Hotels> hotels = availableHotelsResponse.getBody();
        Assert.assertEquals(2,hotels.size());
        Assert.assertEquals(5,hotels.get(0).getRate());
        Assert.assertEquals(3,hotels.get(1).getRate());
    }

    @Test(expected = RequestValidator.InvalidRequestException.class)
    public void givenWrongRequest_WhenCallMockRepository_ThenThrownInvalidRequestException() {
        Request wrongReq = new Request();
        availableHotelsResponse = hotelsControllerMock.getAvailableHotels(wrongReq);
    }

    @Test
    public void givenWrongCityOnRequest_WhenCallMockRepository_ThenThrownInvalidRequestExceptionWithMessageCityCannotBeEmpty() {
        String message = "City cannot be empty";
        Request wrongReq = new Request();
        wrongReq.setNumOfAdults(5);
        wrongReq.setFromDate("2019-05-03");
        wrongReq.setToDate("2019-05-05");
        wrongReq.setCity("");
        try {
            availableHotelsResponse = hotelsControllerMock.getAvailableHotels(wrongReq);
        }catch (RequestValidator.InvalidRequestException e)
        {
            Assert.assertEquals(message,e.getMessage());
        }

    }

    @Test
    public void givenWrongNumberOfAdultsOnRequest_WhenCallMockRepository_ThenThrownInvalidRequestExceptionWithMessageInvalidNumberOfAdults() {
        String message = "Invalid number of adults";
        Request wrongReq = new Request();
        wrongReq.setNumOfAdults(0);
        wrongReq.setFromDate("2019-05-03");
        wrongReq.setToDate("2019-05-05");
        wrongReq.setCity("AUH");
        try {
            availableHotelsResponse = hotelsControllerMock.getAvailableHotels(wrongReq);
        }catch (RequestValidator.InvalidRequestException e)
        {
            Assert.assertEquals(message,e.getMessage());
        }

    }


}