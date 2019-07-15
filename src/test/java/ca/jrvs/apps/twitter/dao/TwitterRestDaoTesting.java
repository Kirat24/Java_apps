package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TwitterRestDaoTesting {

    private Tweet expectedTweet;
    private String id;
    private CrdRepository dao;

    @Before
    public void setup() {

        System.out.println("this is setup for tweet");

        String tweetStr = "hello everyone";
        this.expectedTweet = new Tweet();
        expectedTweet.setText(tweetStr);

        HttpHelper httpHelper = new ApacheHttpHelper();
        this.dao = new TwitterRestDao(httpHelper);
    }
     @Test
     public void create() throws IOException, URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException {
         //prepare tweet text
         Coordinates coordinates = new Coordinates();
         coordinates.setCoordinates(Arrays.asList(50.0, 50.0));
         coordinates.setType("Point");
         expectedTweet.setCoordinates(coordinates);
         System.out.println(JsonUtil.toPrettyJson(expectedTweet));

         //call create method
         Tweet createTweet = (Tweet) dao.create(expectedTweet);
         System.out.println(JsonUtil.toJson(createTweet,true,false));
         //validate tweet object
         assertTweets(expectedTweet, createTweet);
         this.id = createTweet.getIdStr();

         Tweet showTweet = (Tweet) dao.findById(this.id);
         assertTweets(expectedTweet, showTweet);
     }

    public void assertTweets(Tweet expected, Tweet actual) {
        assertNotNull(actual);
        assertNotNull(actual.getIdStr());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getCoordinates(), actual.getCoordinates());
    }


}


