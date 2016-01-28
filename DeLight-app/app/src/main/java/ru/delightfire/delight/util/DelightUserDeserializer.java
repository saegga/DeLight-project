package ru.delightfire.delight.util;

import android.content.Context;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ru.delightfire.delight.entity.subject.DelightRole;
import ru.delightfire.delight.entity.subject.DelightUser;

/**
 * Created by scaredChatsky on 27.01.2016.
 */
public class DelightUserDeserializer implements JsonDeserializer<DelightUser>{

    private Context context;

    public DelightUserDeserializer(Context context){
        this.context = context;
    }

    @Override
    public DelightUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        DelightUser user = UserAccount.getInstance().getUser(this.context);

        user.setFirstName(jsonObject.get("first_name").isJsonNull() ? null : jsonObject.get("first_name").getAsString());
        user.setLastName(jsonObject.get("last_name").isJsonNull() ? null : jsonObject.get("last_name").getAsString());

        if (jsonObject.get("role") != null) {
            user.setRole(DelightRole.valueOf(jsonObject.get("role").getAsString()));
        } else {
            user.setRole(DelightRole.UNASSIGNED);
        }

        user.setPhone(jsonObject.get("phone").isJsonNull() ? null : jsonObject.get("phone").getAsString());

        return user;
    }

}
