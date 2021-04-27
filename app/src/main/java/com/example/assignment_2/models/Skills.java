package com.example.assignment_2.models;

public class Skills {

    private String firstLanguage;
    private String secondLanguage;

    private boolean programming;
    private boolean graphicDesign;
    private boolean videoEditting;
    private boolean vfx;
    private boolean drawing;
    private boolean painting;
    private boolean uiux;
    private boolean translating;
    private boolean writting;
    private boolean contentCreation;

    public String getFirstLanguage() {
        return firstLanguage;
    }

    public void setFirstLanguage(String firstLanguage) {
        this.firstLanguage = firstLanguage;
    }

    public String getSecondLanguage() {
        return secondLanguage;
    }

    public void setSecondLanguage(String secondLanguage) {
        this.secondLanguage = secondLanguage;
    }

    public boolean isProgramming() {
        return programming;
    }

    public void setProgramming(boolean programming) {
        this.programming = programming;
    }

    public boolean isGraphicDesign() {
        return graphicDesign;
    }

    public void setGraphicDesign(boolean graphicDesign) {
        this.graphicDesign = graphicDesign;
    }

    public boolean isVideoEditting() {
        return videoEditting;
    }

    public void setVideoEditting(boolean videoEditting) {
        this.videoEditting = videoEditting;
    }

    public boolean isVfx() {
        return vfx;
    }

    public void setVfx(boolean vfx) {
        this.vfx = vfx;
    }

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public boolean isPainting() {
        return painting;
    }

    public void setPainting(boolean painting) {
        this.painting = painting;
    }

    public boolean isUiux() {
        return uiux;
    }

    public void setUiux(boolean uiux) {
        this.uiux = uiux;
    }

    public boolean isTranslating() {
        return translating;
    }

    public void setTranslating(boolean translating) {
        this.translating = translating;
    }

    public boolean isWritting() {
        return writting;
    }

    public void setWritting(boolean writting) {
        this.writting = writting;
    }

    public boolean isContentCreation() {
        return contentCreation;
    }

    public void setContentCreation(boolean contentCreation) {
        this.contentCreation = contentCreation;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "firstLanguage='" + firstLanguage + '\'' +
                ", secondLanguage='" + secondLanguage + '\'' +
                ", programming=" + programming +
                ", graphicDesign=" + graphicDesign +
                ", videoEditting=" + videoEditting +
                ", vfx=" + vfx +
                ", drawing=" + drawing +
                ", painting=" + painting +
                ", uiux=" + uiux +
                ", translating=" + translating +
                ", writting=" + writting +
                ", contentCreation=" + contentCreation +
                '}';
    }
}
