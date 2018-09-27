package sample.model;

import sample.model.Job;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Student implements Serializable {
    private String mStudentName;
    private Job mCurrentJob;
    private Job mPreviousJob;
    private Job mTentativeJob;
    private List<Job> mJobsDoneThisRotation = new ArrayList<>();

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

    public boolean checkAgainstJobsDoneThisRotation(Job j) {  // if student has already done job this rotation method returns true
        if(mJobsDoneThisRotation == null) return false;
//        else if(mJobsDoneThisRotation.indexOf(j) != mJobsDoneThisRotation.lastIndexOf(j) && j.getNumberOfStudentsRequired() == 2)
//            return mJobsDoneThisRotation.contains(j);
        else return(mJobsDoneThisRotation.contains(j)); // && j.getNumberOfStudentsRequired() == 1);
    }

    public void addJobDoneThisRotation(Job j) {
        mJobsDoneThisRotation.add(j);
    }

    public List<Job> getJobsDoneThisRotation() {
        return mJobsDoneThisRotation;
    }

    public void addMultipleJobsDoneThisRotation(Collection<Job> j) {
        mJobsDoneThisRotation.addAll(j);
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
        mJobsDoneThisRotation.clear();
    }
}
