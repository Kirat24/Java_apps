package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;
import java.util.List;

public interface TwitterService {

    Tweet postTweet(String text, Double latitude, Double longitude);
    Tweet showTweet(String  id, String []fields) throws IOException;
   List<Tweet>  deleteTweet(String []ids) throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException;

}
