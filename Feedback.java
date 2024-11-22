public class Feedback <T1, T2>{
    T1 description;
    T2 rating;

    public Feedback(String description) {
        this.description = (T1)description;

    }
    public Feedback(T2 rating){
        this.rating = rating;
    }
    public Feedback(T1 description, T2 rating){
        this.description = description;
        this.rating = rating;
    }

    T1 getDescription() {
        return description;
    }
    T2 getRating() {
        return rating;
    }
    void setDescription(T1 description) {
        this.description = description;
    }
    void setRating(T2 rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Description: " + description + "\nRating: " + rating;
    }

}


