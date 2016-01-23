package ru.delightfire.delight.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.delightfire.delight.entity.subject.DelightEvent;

/**
 * Created by scaredChatsky on 22.01.2016.
 */
public class DelightEventDeserializer implements JsonDeserializer<DelightEvent>{

    @Override
    public DelightEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        int eventId = jsonObject.get("event_id").getAsInt();
        int placeId = jsonObject.get("place_id").getAsInt();
        String startTime = jsonObject.get("start_time").getAsString();
        String endTime = jsonObject.get("end_time").getAsString();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM");

        Date date = null;
        try {
            date = format.parse(jsonObject.get("date").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new DelightEvent(eventId, placeId, date.getMonth(), date.getDay(), startTime, endTime);
    }

}
