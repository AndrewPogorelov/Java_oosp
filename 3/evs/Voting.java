package evs;

import java.util.Date;
import java.util.List;

public class Voting {
    private String title;
    private Date endDate;
    private List<Candidate> candidates;

    public Voting(String title, Date endDate, List<Candidate> candidates) {
        this.title = title;
        this.endDate = endDate;
        this.candidates = candidates;
    }

    public Voting(User candidate, String format) {
    }

    public String getTitle() {
        return title;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public User getCandidate() {
    }
}