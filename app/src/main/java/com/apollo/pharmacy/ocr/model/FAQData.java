package com.apollo.pharmacy.ocr.model;

public class FAQData {

    public FAQData(String id, String q, String a) {
        this.question = q;
        this.answer = a;
        this.id = id;
    }

    private String question;
    private String answer;
    private boolean isVisible;
    private String id;

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
