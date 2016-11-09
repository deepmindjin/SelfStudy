package kr.co.fastcampus.android.chat;

/**
 * Created by HongSeok on 2016-11-07.
 */

public class FMessage {

    private String id;
    private String text;
    private String name;

    public FMessage() {
    }


    public FMessage(String text, String name) {
        this.text = text;
        this.name = name;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}




