package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.util.StringUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static ca.jrvs.apps.twitter.util.TweetUtil.validId;
@Repository
public class TwitterServiceImp implements TwitterService {

    private CrdRepository dao;
@Autowired
    public TwitterServiceImp(CrdRepository dao){

        this.dao=dao;
    }
    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {

      Tweet postTweet=TweetUtil.buildTweet(text,longitude,latitude);

      TweetUtil.validatePostTweet(postTweet);
      try{

          Tweet responseTweet= (Tweet) dao.create(postTweet);
          printTweet(responseTweet);

      } catch (IOException e) {
          e.printStackTrace();
      } catch (URISyntaxException e) {
          e.printStackTrace();
      } catch (OAuthCommunicationException e) {
          e.printStackTrace();
      } catch (OAuthExpectationFailedException e) {
          e.printStackTrace();
      } catch (OAuthMessageSignerException e) {
          e.printStackTrace();
      }
        return postTweet;
    }

    @Override
    public Tweet showTweet(String id, String[] fields)  {
        Tweet tweet=null;
      if(!validId.test(id)){
          throw new IllegalArgumentException("ID number should contain digits only");
      }
      try {
          tweet = (Tweet) dao.findById(id);
          printTweet(selectFields(tweet, fields));
      }catch (IOException e){
          e.printStackTrace();
      } catch (OAuthExpectationFailedException e) {
          e.printStackTrace();
      } catch (OAuthMessageSignerException e) {
          e.printStackTrace();
      } catch (OAuthCommunicationException e) {
          e.printStackTrace();
      }
        return  tweet;
    }

    @Override
    public List <Tweet> deleteTweet(String[] ids) throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException {

        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids) {
            validId.test(id);
            Tweet tweet = (Tweet) dao.deleteById(id);
            printTweet(tweet);
            tweets.add(tweet);
        }
        return tweets;
    }

    protected void printTweet(Tweet tweet) throws JsonProcessingException {

        System.out.println(JsonParser.toJson(tweet,true,false));
    }

    protected Tweet selectFields(Tweet tweet, String[] fields) throws IOException {
        if (fields == null || fields.length == 0) {
            return tweet;
        }
        //rTweet = deep copy of tweet
        Tweet rTweet = JsonParser.toObjectFromJson(JsonParser.toJson(tweet, true, false), Tweet.class);

        //helper lambda function to remove leading and trailing spaces
        Function<String[], String[]> trimStrArray = (items) -> Arrays.stream(items).map(String::trim)
                .toArray(String[]::new);
        //Make fieldSet for fast lookup and removal
        Set<String> fieldSet = new HashSet<>(Arrays.asList(trimStrArray.apply(fields)));


        Predicate<Method> isSetter = (method) -> method.getName().startsWith("set");
        Arrays.stream(Tweet.class.getMethods())
                .filter(isSetter)
                .forEach(setter ->
                {
                    JsonProperty jsonProperty = setter.getDeclaredAnnotation(JsonProperty.class);
                    if (jsonProperty == null || StringUtil.isEmpty(jsonProperty.value())) {
                        throw new RuntimeException(
                                "@JsonProperty is not defined for method" + setter.getName());
                    }
                    String value = jsonProperty.value();
                    if (fieldSet.contains(value)) {
                        fieldSet.remove(value);
                    } else {
                        try {
                            setter.invoke(rTweet, new Object[]{null});
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("unable to set setter:" + setter.getName(), e);
                        }
                    }
                });


        if (!fieldSet.isEmpty()) {
            String invalidFields = String.join(",", fieldSet);
            throw new RuntimeException("Found invalid select field(s):" + invalidFields);
        }
        return rTweet;

    }
    }
