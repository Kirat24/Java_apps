package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIRunner;
import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class TwitterCLIBean {


    public static void main(String[] args) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIRunner runner = context.getBean(TwitterCLIRunner.class);
        runner.run(args);
    }

    @Bean
    public TwitterCLIRunner twitterCLIRunner(TwitterService service) {
        return new TwitterCLIRunner(service);
    }

    @Bean
    public TwitterService twitterService(CrdRepository dao) {
        return new TwitterServiceImp(dao);
    }

    @Bean
    public CrdRepository<Tweet, String> twitterDao(HttpHelper httpHelper) {
        return new TwitterRestDao(httpHelper);
    }

    @Bean
    HttpHelper helper() {
        return new ApacheHttpHelper();
    }
}
