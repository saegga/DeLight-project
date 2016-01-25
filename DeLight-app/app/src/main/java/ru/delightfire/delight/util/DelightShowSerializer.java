package ru.delightfire.delight.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import ru.delightfire.delight.entity.subject.DelightShow;

/**
 * Created by sergei on 24.01.2016.
 */
public class DelightShowSerializer implements JsonSerializer<DelightShow> {

    @Override
    public JsonElement serialize(DelightShow src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.addProperty("name", src.getExtra());

        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(src.getDay()).length() == 1 ? new StringBuilder("0").append(src.getDay()) : src.getDay());
        builder.append("-");
        builder.append(String.valueOf(src.getMonth()).length() == 1 ? new StringBuilder("0").append(src.getMonth()) : src.getMonth());

        result.addProperty("date", builder.toString());
       // result.addProperty("description", src.getEventId());
        result.addProperty("place_id", src.getPlaceId());
        result.addProperty("start_time", src.getStartTime());
        result.addProperty("end_time", src.getEndTime());
        return result;
    }
}
