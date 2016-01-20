package com.gruppe17.dilema;
//Her laves en class og constructor til at håndtere Dilemmaer
public class Dilemma {
    private long id;
    private String dilemmaTitel;
    private String dilemmaBody;
    private String dilemmaRating;

    public Dilemma(){
        super();
    }

    public Dilemma(long id, String dilemmaTitel, String dilemmaBody, String dilemmaRating) {
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