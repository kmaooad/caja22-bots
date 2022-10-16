package edu.kmaooad;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.json.JSONObject;

import static com.mongodb.client.model.Filters.eq;

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

        final String name = request.getBody().orElse(null);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }
    }
    @FunctionName("TelegramWebhookMessageID")
    public HttpResponseMessage runMessageID(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.FUNCTION)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        final String ObjectName = "message";
        final String FieldName = "message_id";
        final String SuccessResponsePrefix = "200 OK: ";
        final String FailResponse = "BAD REQUEST";

        try
        {
            final String RequestBody = request.getBody().orElse(null);
            if (RequestBody != null)
            {
                JSONObject JSONBody = new JSONObject(RequestBody);
                final int MessageID = JSONBody.getJSONObject(ObjectName).getInt(FieldName);
                return request.createResponseBuilder(HttpStatus.OK).body(SuccessResponsePrefix + MessageID).build();
            }
            else
            {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Body is empty").build();
            }
        }
        catch (Exception e)
        {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(FailResponse).build();
        }
    }

    @FunctionName("TelegramWebhookMessageID")
    public HttpResponseMessage SaveToDBTest(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.FUNCTION)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context)
    {
        context.getLogger().info("Java HTTP trigger processed a request.");

        try {
            final String RequestBody = request.getBody().orElse(null);
            if (RequestBody != null) {
                JSONObject JSONBody = new JSONObject(RequestBody);

                String uri = "mongodb+srv://admin:admin@cluster0.tn0cl.mongodb.net/?retryWrites=true&w=majority";
                try (MongoClient mongoClient = MongoClients.create(uri)) {
                    MongoDatabase database = mongoClient.getDatabase("bots");
                    MongoCollection<Document> collection = database.getCollection("updates");
                    String InsertionResult = collection.insertOne(Document.parse(JSONBody.toString())).toString();
                    return request.createResponseBuilder(HttpStatus.OK).body("Succesfully inserted: " + InsertionResult).build();
                }
            }
        } finally {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
        }
    }


}
