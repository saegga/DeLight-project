package ru.delightfire.delight.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ru.delightfire.delight.entity.subject.DelightShow;

/**
 * Created by sergei on 03.02.2016.
 */
public class DelightShowDeserializer implements JsonDeserializer<DelightShow> {

    @Override
    public DelightShow deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String name = obj.get("show").getAsJsonObject().get("name").getAsString();
        String date = obj.get("show").getAsJsonObject().get("date").getAsString();
        String description = obj.get("show").getAsJsonObject().get("description").getAsString();
        String start = obj.get("show").getAsJsonObject().get("start_time").getAsString();
        String end = obj.get("show").getAsJsonObject().get("end_time").getAsString();
//        String address = json.get("show").getAsJsonObject().get("address").getAsString();
//        String place = json.get("show").getAsJsonObject().get("p_name").getAsString();
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));

        return new DelightShow(month, day, start, end, name, description);
    }
}
