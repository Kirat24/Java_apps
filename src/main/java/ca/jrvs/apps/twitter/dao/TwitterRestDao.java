package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Repository
public class TwitterRestDao  implements CrdRepository<Tweet, String>{

    private HttpHelper httpHelper;



    private static final  String base_uri="https://api.twitter.com";
    public static final String  post_signature="/1.1/statuses/update.json";
    public static final String show_signature="/1.1/statuses/show.json";
    public static final String delete_signature="/1.1/statuses/destroy";

    private  static final String query_sep="?";
    public static final String  ampersand="&";
    public static final String equal="=";


    public static final int HTTP_status_code=200;

    @Autowired
    public TwitterRestDao(HttpHelper httpHelper){

        this.httpHelper=httpHelper;
    }

    @Override
    public Tweet create(Tweet entity) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {

        URI uri;

        try {
            uri = getPostUri(entity);
        }catch (UnsupportedEncodingException|URISyntaxException e )
        {

            throw  new RuntimeException("The reason for failure"+ e );
        }


        HttpResponse response= (HttpResponse) httpHelper.httpPost(uri);


        return validate_response(response,HTTP_status_code);
    }
    @Override
    public Tweet findById(String s) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        URI uri;
        try{

            uri=getShowUri(s);
        } catch (URISyntaxException e) {
            throw new IOException("Invalid uri", e);
        }

        HttpResponse response= (HttpResponse) httpHelper.httpGet(uri);

        return validate_response(response,HTTP_status_code);
    }

    @Override
    public Tweet deleteById(String s) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {

        URI uri;
        try{

            uri=getDelUri(s);
        }catch (URISyntaxException e){
            throw new IOException(e);
        }


        HttpResponse response= (HttpResponse) httpHelper.httpPost(uri);
        return validate_response(response,HTTP_status_code);

    }



    protected URI getPostUri(Tweet tweet) throws UnsupportedEncodingException, URISyntaxException {

        String text=tweet.getText();
        Double longitude=  tweet.getCoordinates().getCoordinates().get(0);
        Double latitude =tweet.getCoordinates().getCoordinates().get(1);

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(base_uri)
                .append(post_signature)
                .append(query_sep);

        appendParam(stringBuilder,"status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()),true);
        appendParam(stringBuilder,"long",longitude.toString(),false);
        appendParam(stringBuilder,"lat",latitude.toString(),false);


        return  new URI(stringBuilder.toString());
    }

    private void appendParam(StringBuilder stringBuilder, String status, String encode) {
    }

    protected  void appendParam(StringBuilder stringBuilder,String key, String value, boolean firstVal){

        if(!firstVal){

            stringBuilder.append(ampersand);
        }
        stringBuilder.append(key)
                .append(equal)
                .append(value);

    }

    protected Tweet validate_response(HttpResponse response, int http_code) throws IOException {


        Tweet tweet =null;
        int status= response.getStatusLine().getStatusCode();
        if(status!=http_code){

           throw new RuntimeException("unexpected status");

        }

        if(response.getEntity()==null)
        {

            throw new RuntimeException("empty body");
        }

        String json_str;

        try {
            json_str = EntityUtils.toString(response.getEntity());

        }catch (IOException e){
            throw new RuntimeException("fail to convert entity o string",e) ;}

        try {
            tweet= JsonUtil.toObjectFromJson(json_str, Tweet.class);
        }catch (IOException e){


            throw new RuntimeException("Unable to convert it",e);
        }
        return tweet;
    }


    protected  URI getShowUri(String id) throws URISyntaxException {


        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(base_uri)
                .append(show_signature)
                .append(query_sep);
        appendParam(stringBuilder,"id",id,true);
        return new URI(stringBuilder.toString());



    }

    protected URI getDelUri(String id) throws URISyntaxException {

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(base_uri)
                .append(delete_signature).append("/").append(id).append(".json");

        return  new URI(stringBuilder.toString());
    }
}


