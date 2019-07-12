package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;

public class TwitterCLIApp {
    public static void main(String[] args) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        //Create components
        HttpHelper httpHelper = new ApacheHttpHelper();
        CrdRepository dao = new TwitterRestDao(httpHelper);
        TwitterService service = new TwitterServiceImp(dao);

        //Create Runner
        TwitterCLIRunner runner = new TwitterCLIRunner(service);

        //Run Application
        runner.run(args);
    }

}
