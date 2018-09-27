package sample.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Classroom implements Serializable {

    private String mClassroomName;
    private List<Job> unassignedJobs = new ArrayList<>();
    private List<Student> mRoster = new ArrayList<Student>();
    private List<Job> mClassroomJobs = new ArrayList<>();

    public Classroom(String ClassroomName, String[] students, HashMap<String, Integer> initiater) {
        mClassroomName = ClassroomName;
        for (String stu : students) {
            mRoster.add(new Student(stu));
        }
        for (String j : initiater.keySet()) {
            mClassroomJobs.add(new Job(j, initiater.get(j)));
        }
    }

    public List<Job> getClassroomJobs() {
        return mClassroomJobs;
    }

    public List<Student> getRoster() {
        return mRoster;
    }

    public String getClassroomName() {
        return mClassroomName;
    }

    public void setClassroomName(String classroomName) {
        mClassroomName = classroomName;
    }

    public static Job findJob(String job, List<Job> classroomJobs) {
        for (Job j : classroomJobs) {
            if (j.equals(job)) return j;
        }
        return null;
    }

    public Student findStudent(String student) {
        for (Student s : getRoster()) {
            if (s.toString().equals(student)) return s;
        }
        return null;
    }

    public void replenishJobs() {
        unassignedJobs.clear();
        for(Job j : mClassroomJobs) {
            unassignedJobs.add(j);
            j.setNumberOfStudentsStillNeeded(j.getNumberOfStudentsRequired());
            if(j.getNumberOfStudentsRequired() == 2) unassignedJobs.add(j);
        }
    }

    public boolean assignNewJobs() {
        replenishJobs();
        restartCycleIfCycleComplete();
        return bruteForceJobAssignments(0);
    }

    private boolean bruteForceJobAssignments(int stuVar) {
        List<Job> qualifiedJobs = mRoster.get(stuVar).getQualifiedJobs(unassignedJobs);  // get list of jobs student has not been assigned.
        boolean result = false;
        do {
            if (qualifiedJobs.size() == 0) return false;  // avoid exception on random int generator
            int j = new Random().nextInt(qualifiedJobs.size());  // get random int
            mRoster.get(stuVar).setTentativeJob(qualifiedJobs.get(j));  // use random int to assign qualified job randomly
            if (stuVar < mRoster.size() - 1) { // confirm student is not last in list
                unassignedJobs.remove(mRoster.get(stuVar).getTentativeJob());
                result = bruteForceJobAssignments(stuVar + 1);  // call and store result of call to bruteForceJobAssignment
                if (result == false) {
                    qualifiedJobs.remove(qualifiedJobs.get(j));  // remove impossible job path root if next student has no qualified job assignments.
                    unassignedJobs.add(mRoster.get(stuVar).getTentativeJob());
                }
            }
            else result = true;
        } while (!result);
        mRoster.get(stuVar).setCurrentJob(mRoster.get(stuVar).getTentativeJob());
        return true;
    }


        // TODO: Fix job assignment algorithm to avoid impossible job assignments.
        // Try a recursive method.
 /*       try {
                for (Student stu : getRoster()) {
                    List<Job> qualifiedJobs = stu.getQualifiedJobs(unassignedJobs);
                    int i = new Random().nextInt(qualifiedJobs.size());
                    stu.setTentativeJob(qualifiedJobs.get(i));
                    unassignedJobs.remove(qualifiedJobs.get(i));
                    qualifiedJobs.get(i).decrementNumberOfStudentsStillNeeded();
                }
        } catch(NullPointerException npe) {
            for(Student stu : mRoster)
                stu.setTentativeJob(null);
            assignNewJobs();
        }
        for(Student stu : mRoster) {
            stu.setCurrentJob(stu.getTentativeJob());
            stu.addJobDoneThisRotation(stu.getTentativeJob());
            stu.setTentativeJob(null);
        }
    }*/

    public void printJobs() {
        for(Student stu : mRoster)
            System.out.printf("%s's job  this week is: %s%n", stu, stu.getCurrentJob());
    }

    public void printPriorJobs() {
        for(Student stu : mRoster) {
            System.out.printf("%s's jobs have been: ", stu);
            for (Job j : stu.getJobsDoneThisRotation())
                System.out.print(j.toString() + " ");
            System.out.println();
        }
    }

    public void loadJobs(String sName, String...jobName) {
        for(String mine : jobName)
            findStudent(sName).addJobDoneThisRotation(findJob(mine, getClassroomJobs()));
    }

    public void restartCycleIfCycleComplete() {
        for(Student s : mRoster)
            if(s.getJobsDoneThisRotation().containsAll(mClassroomJobs))
                for(Student t : mRoster) {
                    s.clearJobsDoneThisRotation();
                }
    }
public void forceRestartCycle() {
        for(Student s : mRoster)
            for(Student t : mRoster) {
                s.clearJobsDoneThisRotation();
            }
    }
}
