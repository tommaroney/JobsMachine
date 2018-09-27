package sample.model;

import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {
    private String mJobTitle;
    private int mNumberOfStudentsRequired;
    private int mNumberOfStudentsStillNeeded;

    public Job(String jobTitle, int numberOfStudentsRequired) {
        mJobTitle = jobTitle;
        mNumberOfStudentsRequired = numberOfStudentsRequired;
    }

    @Override
    public boolean equals(Object o) {
        String j = o.toString();
        if(mJobTitle.equals(j))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return mJobTitle;
    }

    public String getJobTitle() {
        return mJobTitle;
    }

    public void setJobTitle(String jobTitle) {
        mJobTitle = jobTitle;
    }

    public int getNumberOfStudentsRequired() {
        return mNumberOfStudentsRequired;
    }

    public void setNumberOfStudentsRequired(int numberOfStudentsRequired) {
        mNumberOfStudentsRequired = numberOfStudentsRequired;
    }

    public int getNumberOfStudentsStillNeeded() {
        return mNumberOfStudentsStillNeeded;
    }

    public void setNumberOfStudentsStillNeeded(int numberOfStudentsStillNeeded) {
        mNumberOfStudentsStillNeeded = numberOfStudentsStillNeeded;
    }

    public void decrementNumberOfStudentsStillNeeded() {
        mNumberOfStudentsStillNeeded--;
    }
}
