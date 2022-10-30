package edu.kmaooad;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.json.JSONException;
import org.json.JSONObject;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/TelegramWebhook". To invoke it using "curl" command in bash:
     * curl -d "HTTP Body" {your host}/api/TelegramWebhook
     */
    @FunctionName("TelegramWebhook")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.FUNCTION)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        final String RequestBody = request.getBody().orElse(null);
        if (RequestBody == null)
        {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass something to request body").build();
        }

        final String uri = "mongodb+srv://admin:admin@cluster0.tn0cl.mongodb.net/?retryWrites=true&w=majority";
        try
        {
            JSONObject JSONBody = new JSONObject(RequestBody);
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("bots");
            MongoCollection<Document> collection = database.getCollection("updates");
            String InsertionResult = collection.insertOne(Document.parse(JSONBody.toString())).toString();
            return request.createResponseBuilder(HttpStatus.OK).body("Succesfully inserted: " + InsertionResult).build();
        }
        catch (JSONException e)
        {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Unable to parse body").build();
        }
        catch (Exception e)
        {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
        }
    }


}
