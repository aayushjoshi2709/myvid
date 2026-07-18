package com.github.aayushjoshi2709.gateway.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aayushjoshi2709.gateway.entity.enums.HttpMethod;
import io.r2dbc.postgresql.codec.Json;
import org.jspecify.annotations.NullMarked;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import java.util.List;

@Configuration
public class R2dbcCustomConversionsConfig {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Bean
  public R2dbcCustomConversions r2dbcCustomConversions() {
    return R2dbcCustomConversions.of(PostgresDialect.INSTANCE,
            List.of(
                    new RolesToJsonConverter(),
                    new JsonToRolesConverter(),
                    new HttpMethodToStringConverter(),
                    new StringToHttpMethodConverter()));
  }

  @WritingConverter
  static class RolesToJsonConverter implements Converter<List<String>, Json> {
    @Override
    @NullMarked
    public Json convert(List<String> source) {
      try {
        return Json.of(MAPPER.writeValueAsString(source));
      } catch (Exception e) {
        throw new IllegalStateException("Unable to serialize roles to JSON", e);
      }
    }
  }

  @ReadingConverter
  static class JsonToRolesConverter implements Converter<Json, List<String>> {
    @Override
    @NullMarked
    public List<String> convert(Json source) {
      try {
        return MAPPER.readValue(source.asString(), new TypeReference<>() {});
      } catch (Exception e) {
        throw new IllegalStateException("Unable to deserialize roles from JSON", e);
      }
    }
  }

  @WritingConverter
  static class HttpMethodToStringConverter implements Converter<HttpMethod, String> {
    @Override
    public String convert(HttpMethod source) {
      return source.name();
    }
  }

  @ReadingConverter
  static class StringToHttpMethodConverter implements Converter<String, HttpMethod> {
    @Override
    @NullMarked
    public HttpMethod convert(String source) {
      return HttpMethod.valueOf(source);
    }
  }
}
