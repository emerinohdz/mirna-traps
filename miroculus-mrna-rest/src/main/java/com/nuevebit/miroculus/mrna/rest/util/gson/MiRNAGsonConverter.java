/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest.util.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nuevebit.miroculus.mrna.core.Author;
import java.lang.reflect.Type;
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

                    return name.equals("version")
                    || name.equals("id")
                    || name.equals("publications");
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
        builder.registerTypeAdapter(Author.class,
                new JsonSerializer<Author>() {

                    @Override
                    public JsonElement serialize(Author src, 
                            Type typeOfSrc, 
                            JsonSerializationContext context) {

                        return new JsonPrimitive(src.getName());
                    }
                });
        return builder;
    }

}
