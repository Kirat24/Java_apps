package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.StringUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TwitterRestDaoTest {

   private HttpHelper httpHelper;
    private TwitterRestDao dao;
@Before
    public void setup() {

     httpHelper = new ApacheHttpHelper();
        dao = new TwitterRestDao(httpHelper);
    }
    @Test
    public void getPostUri() throws UnsupportedEncodingException, URISyntaxException {

        String uri = "https://api.twitter.com/1.1/statuses/update.json?status=%40abc+text+%23hashtag+2&long=-2.0&lat=1.2";
        URI iUri = dao.getPostUri(TweetUtil.buildTweet("@abc text #hashtag 2", -2.0d, 1.2d));
        assertEquals(uri, iUri.toString());
    }



}