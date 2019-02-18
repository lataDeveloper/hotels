package com.maf.hotels.validator;

import com.maf.hotels.model.Request;

public interface Validator {
    void validate(Request request) throws RuntimeException;
}
