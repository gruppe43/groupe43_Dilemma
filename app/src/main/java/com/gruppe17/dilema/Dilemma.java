package com.gruppe17.dilema;

public class Dilemma {
    private int id;
    private String dilemmaTitel;
    private String dilemmaBody;
    private String dilemmaRating;

    public Dilemma(){
        super();
    }

    public Dilemma(int id, String dilemmaTitel, String dilemmaBody, String dilemmaRating) {
        super();
        this.id = id;
        this.dilemmaTitel = dilemmaTitel;
        this.dilemmaBody = dilemmaBody;
        this.dilemmaRating = dilemmaRating;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.dilemmaTitel + ". " + this.dilemmaBody + ". " + this.dilemmaRating;
    }
}