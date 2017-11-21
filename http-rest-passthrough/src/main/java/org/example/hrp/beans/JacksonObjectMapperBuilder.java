package org.example.hrp.beans;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Viswa Ramamoorthy
 *
 */
public class JacksonObjectMapperBuilder {

	public static Jackson2ObjectMapperBuilder mapper()
    {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        customize(builder);
        return builder;
    }

    public static void customize(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder)
    {
        jackson2ObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jackson2ObjectMapperBuilder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        jackson2ObjectMapperBuilder.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }
}
