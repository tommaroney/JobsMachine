package sample.model;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
    private String mStudentName;
    private Job mCurrentJob;
    private Job mPreviousJob;
    private Job mTentativeJob;
    private Map<Job, Integer> mJobsDoneThisRotation = new HashMap<>(); // stores Job reference and a count of how many times a student has held the job this rotation

    Student(String studentName) {
        mStudentName = studentName;
    }

    @Override
    public String toString() {
        return mStudentName;
    }

    public String getStudentName() {
        return mStudentName;
    }

    public void setStudentName(String studentName) {
        mStudentName = studentName;
    }

    public Job getCurrentJob() {
        return mCurrentJob;
    }

    public void setCurrentJob(Job currentJob) {
        mCurrentJob = currentJob;
    }

    public Job getPreviousJob() {
        return mPreviousJob;
    }

    public void setPreviousJob(Job previousJob) {
        mPreviousJob = previousJob;
    }

    public boolean checkAgainstJobsDoneThisRotation(Job j) {  // if student has already done job this method returns true
        if(mJobsDoneThisRotation == null) return false;
        else return(!(mJobsDoneThisRotation.get(j) < j.getNumberOfStudentsRequired()));
    }

    public void addJobDoneThisRotation(Job j) {
        mJobsDoneThisRotation.replace(j, mJobsDoneThisRotation.get(j) + 1); // increment value associated with Job
    }

    public List<Job> getJobsDoneThisRotation() {
        List<Job> results = new ArrayList<>();
        for(Job j : mJobsDoneThisRotation.keySet())
            if(mJobsDoneThisRotation.get(j) == j.getNumberOfStudentsRequired())
                results.add(j);
        return results;
    }

    public void addMultipleJobsDoneThisRotation(Map<Job, Integer> j) {
        mJobsDoneThisRotation.putAll(j);
    }

    public Job getTentativeJob() {
        return mTentativeJob;
    }

    public void setTentativeJob(Job tentativeJob) {
        mTentativeJob = tentativeJob;
    }

    public List<Job> getQualifiedJobs(List<Job> unassignedJobs) {
        List<Job> filteredJobs = new ArrayList<>();
        for(Job j : unassignedJobs) {
            if (!checkAgainstJobsDoneThisRotation(j))
                filteredJobs.add(j);
        }
        return filteredJobs;
    }

    public void clearJobsDoneThisRotation() {
        for(Job j : mJobsDoneThisRotation.keySet())
            mJobsDoneThisRotation.replace(j, 0);
    }

    public boolean hasStudentDoneEveryJob() {
        for(Job j : mJobsDoneThisRotation.keySet()) {
            if(j.getNumberOfStudentsRequired() == mJobsDoneThisRotation.get(j))
                return true;
        }
        return false;
    }
}
