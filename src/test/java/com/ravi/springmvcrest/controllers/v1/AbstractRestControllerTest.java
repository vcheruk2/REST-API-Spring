package com.ravi.springmvcrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
