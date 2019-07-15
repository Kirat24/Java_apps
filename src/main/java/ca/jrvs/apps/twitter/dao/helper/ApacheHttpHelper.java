package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.util.StringUtil;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;

public class ApacheHttpHelper implements HttpHelper {
    private OAuthConsumer consumer;
    private HttpClient httpClient;

    public ApacheHttpHelper() {
        String consumer_key = System.getenv("consumerKey");
        String consumer_secret_key = System.getenv("consumerKeySecret");
        String access_token = System.getenv("accessToken");
        String token_secret = System.getenv("tokenSecret");

        if (StringUtil.isEmpty(consumer_key, consumer_secret_key, access_token, token_secret)) {
            throw new RuntimeException("Unable to detect key and tokens from System env");
        }

        consumer = new CommonsHttpOAuthConsumer(consumer_key, consumer_secret_key);
        consumer.setTokenWithSecret(access_token, token_secret);
        httpClient = new DefaultHttpClient();

    }


    @Override
    public HttpResponse httpPost(URI uri) {
        try {
            return httpRequest("post", uri, null);

        } catch (IOException | OAuthException e) {
            throw new RuntimeException("failure Occur", e);
        }
    }

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {

        return httpRequest("post", uri, stringEntity);


    }

    @Override
    public HttpResponse httpGet(URI uri) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        HttpResponse httpResponse = null;
        try {
            return httpRequest("get", uri, null);

        } catch (IOException | OAuthException e) {
            throw new RuntimeException("something went wrong ", e);

        }
    }

    public HttpResponse httpRequest(String method, URI uri, StringEntity stringEntity) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {


        if (method.toLowerCase().equals("post")) {

            HttpPost request = new HttpPost(uri);
            if (stringEntity != null)
                request.setEntity(stringEntity);
            consumer.sign(request);
            return httpClient.execute(request);


        } else if (method.toLowerCase().equals("get")) {

            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            return (HttpResponse) httpClient.execute((HttpUriRequest) request);

        } else
            throw new IllegalArgumentException("Please put the valid argument");


    }

}
