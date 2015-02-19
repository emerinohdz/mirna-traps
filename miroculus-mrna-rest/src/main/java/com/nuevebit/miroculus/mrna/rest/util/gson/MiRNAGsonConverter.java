/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest.util.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import org.restlet.ext.gson.GsonConverter;
import org.restlet.ext.gson.GsonRepresentation;
import org.restlet.representation.Representation;

/**
 *
 * @author emerino
 */
public class MiRNAGsonConverter extends GsonConverter {

    final static ExclusionStrategy exclusionStrat
            = new ExclusionStrategy() {

                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    String name = f.getName();

                    return name.equals("version") || name.equals("id");
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            };

    @Override
    protected <T> GsonRepresentation<T> create(Representation source, Class<T> objectClass) {
        return new GsonRepresentation<T>(source, objectClass) {

            @Override
            public GsonBuilder createBuilder() {
                return configureBuilder(super.createBuilder());
            }

        };
    }

    @Override
    protected <T> GsonRepresentation<T> create(T source) {
        return new GsonRepresentation<T>(source) {

            @Override
            protected GsonBuilder createBuilder() {
                return configureBuilder(super.createBuilder());
            }

        };
    }

    private GsonBuilder configureBuilder(GsonBuilder builder) {
        builder.addDeserializationExclusionStrategy(exclusionStrat);
        builder.addSerializationExclusionStrategy(exclusionStrat);
        return builder;
    }

}
