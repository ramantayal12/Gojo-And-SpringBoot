package org.example.serialisation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * In this configuration, a single instance of ObjectMapper will be created and used throughout the
 * application context. This is because the @Bean annotation is used on the init() method,
 * indicating that it should be managed as a Spring bean. By default, Spring manages singleton
 * beans, which means that only one instance of the bean will be created and shared across all
 * components that depend on it.
 * <p>
 * So, every time the SerialisationUtil bean is injected into another component or accessed within
 * the application, it will reference the same instance of ObjectMapper that was created during the
 * initialization process. This ensures consistency and avoids unnecessary overhead from creating
 * multiple instances of ObjectMapper.
 */

// Note : creating a static method this way isn't preferred
@Configuration
public class SerialisationUtil {

  // ObjectMapper instance to handle JSON serialization/deserialization
  private static ObjectMapper objectMapper;

  // Method to serialize an object to JSON string
  public static String serialize(Object object) throws JsonProcessingException {
    // Using the objectMapper to convert the object to JSON string
    return objectMapper.writeValueAsString(object);
  }

  // Method to deserialize a JSON string to an object of specified class
  public static <T> T deserialize(String value, Class<T> referenceClass)
      throws JsonProcessingException {
    // Using the objectMapper to convert the JSON string to the specified class
    return objectMapper.readValue(value, referenceClass);
  }

  // Bean initialization method to create and return an instance of ObjectMapper
  @Bean
  public ObjectMapper init() {
    // Creating a new instance of ObjectMapper
    objectMapper = new ObjectMapper();

    // Returning the created instance
    return objectMapper;
  }

}
