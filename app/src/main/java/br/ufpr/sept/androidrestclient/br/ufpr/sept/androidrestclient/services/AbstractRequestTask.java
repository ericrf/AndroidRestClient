package br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Eric on 16/03/2017.
 */

abstract class AbstractRequestTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    public RestTemplate createRestTemplate(){
        RestTemplate template = new RestTemplate();
        //template.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor[]{new Interceptor()}));
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template;
    }

    public class Interceptor implements ClientHttpRequestInterceptor{

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            traceRequest(request, body);
            ClientHttpResponse response = execution.execute(request, body);
            traceResponse(response);
            return response;
        }

        private void traceRequest(HttpRequest request, byte[] body) throws IOException {
            Log.d("Request     : ","");
            Log.d("URI         : ", request.getURI().toString());
            Log.d("Method      : ", request.getMethod().toString());
            Log.d("Headers     : ", request.getHeaders().toString());
            Log.d("Request body: ", new String(body, "UTF-8"));
        }

        private void traceResponse(ClientHttpResponse response) throws IOException {
            StringBuilder inputStringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
            Log.d("Response     : ","");
            Log.d("Status code  : ", response.getStatusCode().toString());
            Log.d("Status text  : ", response.getStatusText());
            Log.d("Headers      : ", response.getHeaders().toString());
            Log.d("Response body: ", inputStringBuilder.toString());
        }
    }
}
