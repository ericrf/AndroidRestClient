package br.ufpr.sept.androidrestclient.services;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Eric on 16/03/2017.
 */

abstract class AbstractRequestTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    public RestTemplate createRestTemplate(){
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template;
    }
}
