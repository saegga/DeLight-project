package ru.delightfire.delight.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.entity.subject.DelightShow;

/**
 * Created by sergei on 10.01.2016.
 */
public class DelightShowDeserializer implements JsonDeserializer<List<DelightShow>> {
    @Override
    public List<DelightShow> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonArray array = object.getAsJsonArray("show");
        String name;
        String date;
        String description;
        String place;
        String money;
        int custom_id;
        List<DelightShow> listShow = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonElement element = array.get(i);
            for (int j = 0; j < element.getAsJsonArray().size(); j++) {
                name = element.getAsJsonArray().get(j).getAsJsonObject().get("name").getAsString();
                date = element.getAsJsonArray().get(j).getAsJsonObject().get("date").getAsString();
                description = element.getAsJsonArray().get(j).getAsJsonObject().get("description").getAsString();
                place = element.getAsJsonArray().get(j).getAsJsonObject().get("place").getAsString();
                money = element.getAsJsonArray().get(j).getAsJsonObject().get("money").getAsString();
                //custom_id = element.getAsJsonArray().get(j).getAsJsonObject().get("custom_id").getAsInt();

                listShow.add(new DelightShow(date, description, name, money, place));
            }
        }

        return listShow;
    }
}
