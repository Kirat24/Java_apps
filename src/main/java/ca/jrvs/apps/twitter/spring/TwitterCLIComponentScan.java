package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIRunner;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class TwitterCLIComponentScan {


    public static void main(String[] args) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        ApplicationContext context=new AnnotationConfigApplicationContext(
                TwitterCLIComponentScan.class);
        TwitterCLIRunner runner = context.getBean(TwitterCLIRunner.class);
        runner.run(args);
    }
}
