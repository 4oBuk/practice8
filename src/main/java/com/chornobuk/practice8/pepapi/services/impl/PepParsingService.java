package com.chornobuk.practice8.pepapi.services.impl;

import com.chornobuk.practice8.pepapi.entities.Pep;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@AllArgsConstructor
public class PepParsingService {
    private MongoTemplate template;

    private ObjectMapper objectMapper;
//    todo: add interface
    public void writeFirmJsonToDB(String path) {
        template.remove(new Query(), "pep");
        File data = new File(path);
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(data)) {
            System.out.println("started writing");
            if (parser.nextToken() == JsonToken.START_ARRAY) {
                while (parser.nextToken() == JsonToken.START_OBJECT) {
                    Pep pep = objectMapper.readValue(parser, Pep.class);
                    template.save(pep);
                }
            }
            System.out.println("data has been written");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
