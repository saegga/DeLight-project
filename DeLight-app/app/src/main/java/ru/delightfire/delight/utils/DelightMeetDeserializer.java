package ru.delightfire.delight.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.entity.DelightMeeting;
import ru.delightfire.delight.entity.DelightShow;

/**
 * Created by sergei on 10.01.2016.
 */
public class DelightMeetDeserializer implements JsonDeserializer<List<DelightMeeting>> {

    @Override
    public List<DelightMeeting> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonArray array = object.getAsJsonArray("meet");
        String name;
        String agenda;
        String place;
        String date;
        List<DelightMeeting> meetList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonElement element = array.get(i);
            for (int j = 0; j < element.getAsJsonArray().size(); j++) {
                name = element.getAsJsonArray().get(j).getAsJsonObject().get("name").getAsString();
                agenda = element.getAsJsonArray().get(j).getAsJsonObject().get("agenda").getAsString();
                place = element.getAsJsonArray().get(j).getAsJsonObject().get("place").getAsString();
                date = element.getAsJsonArray().get(j).getAsJsonObject().get("date").getAsString();
                meetList.add(new DelightMeeting(date, agenda, name, place));
            }
        }
        return meetList;
    }
}
