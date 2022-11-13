package edu.kmaooad;

import com.microsoft.azure.functions.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.*;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


/**
 * Unit test for Function class.
 */
public class FunctionTest {
    /**
     * Unit test for HttpTriggerJava method.
     */

    private HttpResponseMessage getResponse(final Optional<String> queryBody)
    {
        final HttpRequestMessage<Optional<String>> req = mock(HttpRequestMessage.class);

        doReturn(queryBody).when(req).getBody();

        doAnswer(new Answer<HttpResponseMessage.Builder>() {
            @Override
            public HttpResponseMessage.Builder answer(InvocationOnMock invocation) {
                HttpStatus status = (HttpStatus) invocation.getArguments()[0];
                return new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status);
            }
        }).when(req).createResponseBuilder(any(HttpStatus.class));

        final ExecutionContext context = mock(ExecutionContext.class);
        doReturn(Logger.getGlobal()).when(context).getLogger();
        return new TelegramWebhookHandler().run(req, context);
    }

    @Test
    public void testHttpTriggerJava() throws Exception {

        final String invalidJSONString = "Definitely not JSON object";
        final HttpResponseMessage ret = getResponse(Optional.of(invalidJSONString));

        // Verify
        assertEquals(ret.getStatus(), HttpStatus.BAD_REQUEST);
    }
    @Test
    public void testSuccessfulTriggerJava() throws Exception {

        final String successfulMessage = "{\"update_id\":983721550,\n" +
                "\"message\":{\"message_id\":6,\"from\":{\"id\":421325006,\"is_bot\":false,\"first_name\":\"Alexandra\"" +
                ",\"last_name\":\"Shliakhova\",\"username\":\"Sun_0k\",\"language_code\":\"ru\"}," +
                "\"chat\":{\"id\":421325006,\"first_name\":\"Alexandra\",\"last_name\":\"Shliakhova\"," +
                "\"username\":\"Sun_0k\",\"type\":\"private\"},\"date\":1664723185,\"text\":\"hello, world\"}}";

        final HttpResponseMessage ret = getResponse(Optional.of(successfulMessage));
        // Verify
        assertEquals(ret.getStatus(), HttpStatus.OK);
    }
    @Test
    public void testEmptyBodyTriggerJava() throws Exception {

        final HttpResponseMessage ret = getResponse(Optional.empty());
        // Verify
        assertEquals(ret.getStatus(), HttpStatus.BAD_REQUEST);
    }
}
