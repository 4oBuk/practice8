package com.chornobuk.practice8.pepapi.services.impl;

import com.chornobuk.practice8.pepapi.entities.Pep;
import com.chornobuk.practice8.pepapi.services.PepParsingService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@AllArgsConstructor
@Slf4j
public class PepParsingServiceImpl implements PepParsingService {
    private final MongoTemplate template;

    private final ObjectMapper objectMapper;
    public void writeJsonFileToDB(String path) {
        template.remove(new Query(), "pep");
        File data = new File(path);
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(data)) {
            log.debug("started writing");
            if (parser.nextToken() == JsonToken.START_ARRAY) {
                while (parser.nextToken() == JsonToken.START_OBJECT) {
                    Pep pep = objectMapper.readValue(parser, Pep.class);
                    template.save(pep);
                }
            }
            log.debug("data has been written");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
