package org.elsys.ip.blogpost.blogpost;

import java.util.UUID;

public class Blogpost {

    private String id;
    private String author;
    private String title;
    private String content;

    public Blogpost(String id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Blogpost(String author, String title, String content) {
        this(UUID.randomUUID().toString(), author, title, content);
    }

    public Blogpost() {
        this("", "", "");
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
