package ru.delightfire.delight.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.entity.DelightEvent;
import ru.delightfire.delight.entity.DelightTraining;

/**
 * Created by scaredChatsky on 06.12.2015.
 * <p/>
 * TODO: create Gson deserealizer
 */
public class DelightTrainingDeserializer implements JsonDeserializer<ArrayList<DelightTraining>> {


    @Override
    public ArrayList<DelightTraining> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray array = jsonObject.getAsJsonArray("training");
        String agenda;
        String name;
        String dayOfWeek;
        String time;
        String place;
        String ownerLogin;
        ArrayList<DelightTraining> listTrainings = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonElement element = array.get(i);
            for (int j = 0; j < element.getAsJsonArray().size(); j++) {
                name = element.getAsJsonArray().get(j).getAsJsonObject().get("name").getAsString();
                agenda = element.getAsJsonArray().get(j).getAsJsonObject().get("agenda").getAsString();
                dayOfWeek = element.getAsJsonArray().get(j).getAsJsonObject().get("dayOfWeek").getAsString();
                time = element.getAsJsonArray().get(j).getAsJsonObject().get("time").getAsString();
                if (element.getAsJsonArray().get(j).getAsJsonObject().get("place").isJsonNull()) {
                    place = "";
                } else {
                    place = element.getAsJsonArray().get(j).getAsJsonObject().get("place").getAsString();
                }
                ownerLogin = element.getAsJsonArray().get(j).getAsJsonObject().get("owner_login").getAsString();
                listTrainings.add(new DelightTraining(agenda, name, dayOfWeek, time, place, ownerLogin, null));
            }

        }
        return listTrainings;
    }

}////
// TODO: 09.01.2016 сделать массив событий (здесь нужно сформировать массив тренировок)
// todo + mysql запрос : select concat_ws(' - ', start_time, end_time) as time, ... from trainings;
// todo задокументировать все
//
