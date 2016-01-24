package ru.delightfire.delight.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import ru.delightfire.delight.entity.subject.DelightTraining;

/**
 * Created by scaredChatsky on 24.01.2016.
 */
public class DelightTrainingSerealizer implements JsonSerializer<DelightTraining>{

    @Override
    public JsonElement serialize(DelightTraining src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.addProperty("owner_id", 1);
        result.addProperty("start_time", src.getStartTime());
        result.addProperty("end_time", src.getEndTime());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.valueOf(src.getDay()).length() == 1 ? new StringBuilder("0").append(src.getDay()) : src.getDay());
        stringBuilder.append("-");
        stringBuilder.append(String.valueOf(src.getMonth()).length() == 1 ? new StringBuilder("0").append(src.getMonth()) : src.getMonth());

        result.addProperty("date", stringBuilder.toString());
        result.addProperty("place_id", src.getPlaceId());

        return result;
    }

}
