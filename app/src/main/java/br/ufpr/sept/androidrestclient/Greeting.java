package br.ufpr.sept.androidrestclient;

/**
 * Created by Eric on 04/03/2017.
 */

class Greeting {
    private String id;
    private String content;

    public Greeting(){}

    public Greeting(String id, String content){
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }
}
