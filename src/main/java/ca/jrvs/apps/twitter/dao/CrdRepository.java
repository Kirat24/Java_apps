package ca.jrvs.apps.twitter.dao;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public interface CrdRepository<T ,ID> {

    T create(T entity) throws IOException, URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException;
    T findById(ID id) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException;
    T deleteById(ID id) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException;



}
