import java.time.Year;

public class VotingMachine {
    Year year;

    public VotingMachine(Year year) {
        if (year == null) {
            throw new NullPointerException();
        }
        this.year = year;
    }

    public VotingMachine(){
        this.year = Year.now();
    }


}
