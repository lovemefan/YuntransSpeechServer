/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 20:43
 */
package com.yuntrans.common.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Json {
    private static final Logger log = LoggerFactory.getLogger(Json.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final ObjectMapper MAPPER_WITH_INDENT = new ObjectMapper();

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private static final ObjectWriter WRITER = MAPPER.writer();

    private static final ObjectReader READER = MAPPER.reader();

    static {
        MAPPER_WITH_INDENT.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER_WITH_INDENT.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private static final ObjectWriter WRITER_WITH_INDENT = MAPPER_WITH_INDENT.writer();

    public static String toString(Object object) throws JsonException {
        try {
            return WRITER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException("Failed to convert object to json string!", e);
        }
    }

    public static String toStringOrEmpty(Object object) {
        try {
            return WRITER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("Failed process json:", (Throwable)e);
            return "";
        }
    }

    public static String toStringOrNull(Object object) {
        try {
            return WRITER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("Failed to parse json:", (Throwable)e);
            return null;
        }
    }

    public static String toStringWithIndent(Object object) throws JsonException {
        try {
            return WRITER_WITH_INDENT.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException("Failed to convert object to json string!", e);
        }
    }

    public static String toStringWithIndentOrEmpty(Object object) {
        try {
            return toStringWithIndent(object);
        } catch (JsonException e) {
            log.info("Failed process json:", e);
            return "";
        }
    }

    public static String toStringWithIndentOrNull(Object object) {
        try {
            return toStringWithIndent(object);
        } catch (JsonException e) {
            log.info("Failed process json:", e);
            return null;
        }
    }

    public static <T> T toObject(String text, Class<T> clazz) throws JsonException {
        ObjectReader reader = MAPPER.readerFor(clazz);
        try {
            return (T)reader.readValue(text);
        } catch (IOException e) {
            log.error("Bad json string: {}", text, e);
            throw new JsonException("Failed to convert json string to java object!", e);
        }
    }

    public static <T> T toObject(JsonNode json, Class<T> clazz) throws JsonException {
        ObjectReader reader = MAPPER.readerFor(clazz);
        try {
            return (T)reader.readValue(json);
        } catch (IOException e) {
            throw new JsonException("Failed to convert json tree to java object!", e);
        }
    }

    public static <T> T toObject(String text, TypeReference<T> type) throws JsonException {
        ObjectReader reader = MAPPER.readerFor(type);
        try {
            return (T)reader.readValue(text);
        } catch (IOException e) {
            log.error("Bad json string: {}", text, e);
            throw new JsonException("Failed to convert json string to java object!", e);
        }
    }

    public static <T> T toObjectOrNull(String text, Class<T> clazz) {
        try {
            return toObject(text, clazz);
        } catch (JsonException e) {
            log.error("Bad json string: {}", text, e);
            log.info("Failed process json:", e);
            return null;
        }
    }

    public static <T> T toObjectOrNull(JsonNode json, Class<T> clazz) {
        try {
            return toObject(json, clazz);
        } catch (JsonException e) {
            log.info("Failed process json:", e);
            return null;
        }
    }

    public static <T> List<T> toObjectList(String text, Class<T> clazz) throws JsonException {
        CollectionType type = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
        ObjectReader reader = MAPPER.readerFor((JavaType)type);
        try {
            return (List<T>)reader.readValue(text);
        } catch (IOException e) {
            log.error("Bad json string: {}", text, e);
            throw new JsonException("Failed to convert json string to java object list!", e);
        }
    }

    public static <T> List<T> toObjectListOrNull(String text, Class<T> clazz) {
        CollectionType type = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
        ObjectReader reader = MAPPER.readerFor((JavaType)type);
        try {
            return (List<T>)reader.readValue(text);
        } catch (IOException e) {
            log.info("Failed process json:", e);
            return null;
        }
    }

    public static <T> Map<String, T> toObjectMap(String text, Class<T> clazz) throws JsonException {
        MapType type = MAPPER.getTypeFactory().constructMapType(Map.class, String.class, clazz);
        ObjectReader reader = MAPPER.readerFor((JavaType)type);
        try {
            return (Map<String, T>)reader.readValue(text);
        } catch (IOException e) {
            log.error("Bad json string: {}", text, e);
            throw new JsonException("Failed to convert json string to java object map!");
        }
    }

    public static <T> Map<String, T> toObjectMapOrNull(String text, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(text))
                return null;
            return toObjectMap(text, clazz);
        } catch (JsonException e) {
            log.info("Failed process json:", e);
            return null;
        }
    }

    public static <T> Map<String, T> toObjectMapOrNull(ObjectNode json, Class<T> clazz) {
        MapType type = MAPPER.getTypeFactory().constructMapType(Map.class, String.class, clazz);
        ObjectReader reader = MAPPER.readerFor((JavaType)type);
        try {
            return (Map<String, T>)reader.readValue((JsonNode)json);
        } catch (IOException e) {
            return null;
        }
    }

    public static JsonNode toTree(String text) throws JsonException {
        try {
            return READER.readTree(text);
        } catch (IOException e) {
            throw new JsonException("Failed to convert string to json tree!", e);
        }
    }

    public static JsonNode toTree(Object object) {
        return MAPPER.valueToTree(object);
    }

    public static JsonNode toTreeOrNull(String text) {
        try {
            return toTree(text);
        } catch (JsonException e) {
            log.info("Failed process json: {}", text, e);
            return null;
        }
    }

    public static ObjectNode toTreeObject(String text) throws JsonException {
        try {
            JsonNode node = READER.readTree(text);
            if (node instanceof ObjectNode)
                return (ObjectNode)node;
            throw new JsonException("Failed to convert string to json tree!");
        } catch (IOException e) {
            throw new JsonException("Failed to convert string to json tree!", e);
        }
    }

    public static ObjectNode toTreeObject(Object object) throws JsonException {
        JsonNode node = MAPPER.valueToTree(object);
        if (node instanceof ObjectNode)
            return (ObjectNode)node;
        throw new JsonException("Failed to convert object to json tree!");
    }

    public static ObjectNode toTreeObjectOrNull(Object object) {
        JsonNode node = MAPPER.valueToTree(object);
        if (node instanceof ObjectNode)
            return (ObjectNode)node;
        return null;
    }

    public static ObjectNode toTreeObjectOrNull(String text) {
        try {
            JsonNode node = READER.readTree(text);
            if (node instanceof ObjectNode)
                return (ObjectNode)node;
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayNode toTreeArray(String text) throws JsonException {
        try {
            JsonNode node = READER.readTree(text);
            if (node instanceof ArrayNode)
                return (ArrayNode)node;
            throw new JsonException("Failed to convert string to json tree!");
        } catch (IOException e) {
            throw new JsonException("Failed to convert string to json tree!", e);
        }
    }

    public static ObjectNode newTreeObject() {
        return MAPPER.createObjectNode();
    }

    public static ArrayNode newTreeArray() {
        return MAPPER.createArrayNode();
    }

    public static byte[] toBytes(Object object) throws JsonException {
        try {
            return MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new JsonException("Failed to convert object to bytes!", e);
        }
    }

    public static byte[] toBytesOrNull(Object object) {
        try {
            return MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            log.info("Failed process json:", (Throwable)e);
            return null;
        }
    }

    public static Map toMap(ObjectNode objectNode) {
        return Optional.<ObjectNode>ofNullable(objectNode).map(node -> (HashMap)MAPPER.convertValue(node, HashMap.class)).orElse(new HashMap<>(0));
    }
}