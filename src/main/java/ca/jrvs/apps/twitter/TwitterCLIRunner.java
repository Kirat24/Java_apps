package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.StringUtil;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
@Repository
public class TwitterCLIRunner {

    private TwitterService service;
    public static final String colon=":";
    public static final String comma=",";

@Autowired
    public TwitterCLIRunner(TwitterService service){

        this.service=service;

    }

    public  void run(String[] args) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {

        if(args.length<2){

            throw new RuntimeException("Cannot run this") ;}

        switch (args[0]){
            case "post":
                postTweet(args);
                break;
            case "show":
                showTweet(args);
                break;
            case "delete":
                deleteTweet(args);
                break;
             default:
                 System.out.println("You can only post|show|delete");
                 break;
        }


    }

     protected void postTweet(String[] args){
        String text=args[1];
         String coor=args[2];
         String[] coordArray=coor.split(colon);

         if(coordArray.length!=2|| StringUtil.isEmpty(text)){

             throw new RuntimeException("Either location lor text is not in proper format");
         }
        Double lat=Double.parseDouble(coordArray[0]);
        Double longi=Double.parseDouble(coordArray[1]);

        service.postTweet(text,lat,longi);

     }

     protected void showTweet(String [] args) throws IOException {


         if (args.length < 2) {
             throw new RuntimeException("USAGE: TwitterCLIApp show tweet_id [fields]");
         }
         String[] fieldsArray = null;
         String tweet_id = null;

         switch (args.length) {
             case 3:
                 String fields = args[2];
                 if (StringUtil.isEmpty(fields)) {
                     throw new RuntimeException(
                             "Error: empty fields. USAGE: TwitterCLIApp show tweet_id [fields]");
                 }
                 fieldsArray = fields.split(comma);
             case 2:
                 tweet_id = args[1];
                 if (StringUtil.isEmpty(tweet_id)) {
                     throw new RuntimeException(
                             "Error: Empty ID\nUSAGE: TwitterCLIApp show tweet_id [fields]");
                 }
     }
         service.showTweet(tweet_id, fieldsArray);

     }


    protected void deleteTweet(String[] args) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        if (args.length != 2 || StringUtil.isEmpty(args[1])) {
            throw new RuntimeException("USAGE: TwitterCLIApp deleteTweets tweet_ids");
        }

        String tweetIds = args[1];
        String[] ids = tweetIds.split(comma);
        service.deleteTweet(ids);
    }
}

