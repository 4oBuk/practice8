package com.chornobuk.practice8.celebapi.services.imp;

import com.chornobuk.practice8.celebapi.entities.Celebrity;
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
public class CelebrityParsingService {
    private MongoTemplate template;

    private ObjectMapper objectMapper;

    public void writeFirmJsonToDB(String path) {
        template.remove(new Query(), "celebrity");
        File data = new File(path);
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(data)) {
            System.out.println("started writing");
            if (parser.nextToken() == JsonToken.START_ARRAY) {
                while (parser.nextToken() == JsonToken.START_OBJECT) {
                    Celebrity celebrity = objectMapper.readValue(parser, Celebrity.class);
                    template.save(celebrity);
                }
            }
            System.out.println("data has been written");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
