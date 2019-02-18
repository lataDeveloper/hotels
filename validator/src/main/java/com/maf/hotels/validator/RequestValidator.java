package com.maf.hotels.validator;

import com.maf.hotels.model.Request;

import java.time.LocalDate;

//this class used for validate request of my service to sure the request is valid
public class RequestValidator implements Validator{
    @Override
    public void validate(Request request) throws InvalidRequestException {
        if(request == null)
            throw new InvalidRequestException("Request cannot be empty");

        if(!validDate(request))
            throw new InvalidRequestException("From Date cannot be after before date");

        if(!validCity(request))
            throw new InvalidRequestException("City cannot be empty");

        if(!validNumberOfAdults(request.getNumOfAdults()))
            throw new InvalidRequestException("Invalid number of adults");
    }

    private boolean validNumberOfAdults(int numOfAdults) {
        return numOfAdults>0;
    }

    private boolean validCity(Request request) {
       return request.getCity()!=null || !request.getCity().isEmpty();


    }

    private boolean validDate(Request request) {
        if(request.getFromDate() !=null && request.getToDate()!= null) {
            LocalDate fromDate = LocalDate.parse(request.getFromDate());
            LocalDate toDate = LocalDate.parse(request.getToDate());
            return fromDate.isBefore(toDate);
        }
        throw new InvalidRequestException("Dates cannot be null");

    }


    public class InvalidRequestException extends RuntimeException {
         InvalidRequestException(String message){
            super(message);
        }
    }
}
