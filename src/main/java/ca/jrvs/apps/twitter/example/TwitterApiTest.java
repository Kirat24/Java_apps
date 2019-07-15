package ca.jrvs.apps.twitter.example;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.Arrays;

public class TwitterApiTest {



    private static String consumer_key=System.getenv("consumerKey");
    private static String consumer_secret_key=System.getenv("consumerKeySecret");
    private static String access_token=System.getenv("accessToken");
    private static String token_secret=System.getenv("tokenSecret");


    public static void main(String[] args) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {

        OAuthConsumer consumer=new CommonsHttpOAuthConsumer(consumer_key,consumer_secret_key);
        consumer.setTokenWithSecret(access_token,token_secret);

        HttpGet request=new HttpGet
                ("https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump");



        //sign the request

        consumer.sign(request);

        System.out.println(" HTTPs REQUEST HEADERS");
        Arrays.stream(request.getAllHeaders())
              .forEach(System.out::println);

        HttpClient httpClient=new DefaultHttpClient();
        HttpResponse response=httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
