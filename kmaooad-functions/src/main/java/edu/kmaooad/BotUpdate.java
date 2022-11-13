package edu.kmaooad;

import com.microsoft.azure.functions.HttpStatus;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

@Data
public class BotUpdate {

    public BotUpdate(final String rawUpdate) {
        //todo: get rid of parsing dependency
        try {
            JSONObject JSONBody = new JSONObject(rawUpdate);
            JSONObject JSONBody1 = JSONBody.getJSONObject("message");
            text = JSONBody1.getString("text");
            messageId = String.valueOf(JSONBody1.getInt("message_id"));
        } catch (JSONException ignored) {}
    }

    private String messageId;
    private String text;

    public String getMessageId() {
        return messageId;
    }

    public String getText() {
        return text;
    }
}
