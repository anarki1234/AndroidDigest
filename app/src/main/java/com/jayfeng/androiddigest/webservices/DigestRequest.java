package com.jayfeng.androiddigest.webservices;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.jayfeng.androiddigest.webservices.json.DigestJson;

public class DigestRequest extends BaseGoogleHttpClientSpiceRequest<DigestJson> {

    public DigestRequest() {
        super(DigestJson.class);
    }

    @Override
    public DigestJson loadDataFromNetwork() throws Exception {

        HttpRequest request = null;
        GenericUrl genericUrl = new GenericUrl(url);

        if (postParameters == null) {
            request = getHttpRequestFactory().buildGetRequest(genericUrl);
        } else {
            HttpContent content = new UrlEncodedContent(postParameters);
            request = buildPostRequest(genericUrl, content);
        }
        request.setParser(new JacksonFactory().createJsonObjectParser());

        return request.execute().parseAs(getResultType());
    }

}
