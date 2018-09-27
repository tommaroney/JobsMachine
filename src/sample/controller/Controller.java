package sample.controller;

import sample.model.Classroom;
import sample.model.Job;
import sample.model.Student;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;

public class Controller {


    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Classroom paigesRoom;
    private Classroom paigesRoom2;


    public Controller(String classroomName, String[] classList, HashMap<String, Integer> initiateJobs) {


        paigesRoom = new Classroom(classroomName, classList, initiateJobs);
        testRunProgram();

/*        try (ObjectOutputStream objOStrm = new ObjectOutputStream(new FileOutputStream(paigesRoom.getClassroomName())) )
        {
            objOStrm.writeObject(paigesRoom);
        } catch(IOException ioe) {
            System.out.println("Exception during serialization: " + ioe);
        }*/
    }

    /*public Controller(String fileName) {
        try ( ObjectInputStream objIStrm = new ObjectInputStream(new FileInputStream(fileName)) )
        {
            paigesRoom2 = (Classroom)objIStrm.readObject();

        } catch(Exception e) {
            System.out.println("Exception during deserialization: " + e);
        }
            paigesRoom2.printPriorJobs();
            paigesRoom2.assignNewJobs();
            paigesRoom2.printJobs();
    }*/


    public void manuallyAddPriorJobs() {
        System.out.println("How many jobs have students already been assigned?  ");
        int numberOfWeeksPassed = 0;
        try {
            numberOfWeeksPassed = Integer.valueOf(reader.readLine()); //have reader enter number of jobs they need to add
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Student stu : paigesRoom.getRoster()) {
            for(int i = 0; i < numberOfWeeksPassed; i++) {
                Job j = null;
                do {
                    System.out.printf("What job did %s have week %d?  ", stu, i + 1);
                    try {
                        j = paigesRoom.findJob(reader.readLine(), paigesRoom.getClassroomJobs());
                        if(stu.checkAgainstJobsDoneThisRotation(j)) {
                            System.out.println("Would you like to change your answer? That job was already " +
                                    "assigned to this student. Y or N: ");
                            if (reader.readLine() == "Y") {
                                j = null;
                                System.out.println("Please try again");
                            }
                        }
                    } catch (IOException e){
                            e.printStackTrace();
                    }
                }while(j == null);
                    stu.addJobDoneThisRotation(j);
            }
        }
    }

    public void testRunProgram() {
        paigesRoom.loadJobs("Max", "Lost and Found", "Postal Service", "Line Leader");
        paigesRoom.loadJobs("Lydia", "Pencils", "Custodian", "Trash and Recycling");
        paigesRoom.loadJobs("Kelsey", "Line Leader", "Teacher's Assistant", "Pencils");
        paigesRoom.loadJobs("Maia", "Material Manager", "Lost and Found", "Chair Checkers");
        paigesRoom.loadJobs("Roe", "Swipe and Wipe", "Electrician", "Postal Service");
        paigesRoom.loadJobs("Mollie", "Caboose", "Chair Checkers", "Pencils");
        paigesRoom.loadJobs("Andre", "Postal Service", "Pencils", "Chair Checkers");
        paigesRoom.loadJobs("Cam", "Chair Checkers", "Swipe and Wipe", "Custodian");
        paigesRoom.loadJobs("Claire", "Trash and Recycling", "Pencils", "Lost and Found");
        paigesRoom.loadJobs("Ellory", "Postal Service", "Chair Checkers", "Substitute");
        paigesRoom.loadJobs("Ryan", "Pencils", "Caboose", "Swipe and Wipe");
        paigesRoom.loadJobs("Sulley", "Chair Checkers", "Trash and Recycling", "Teacher's Assistant");
        paigesRoom.loadJobs("Tobias", "Custodian", "Postal Service", "Material Manager");
        paigesRoom.loadJobs("Matthew", "Teacher's Assistant", "Substitute", "Postal Service");
        paigesRoom.loadJobs("Kendall", "Substitute", "Line Leader", "Caboose");
        paigesRoom.loadJobs("Maddie", "Electrician", "Material Manager", "Electrician");

        paigesRoom.printPriorJobs();

//        sample.manuallyAddPriorJobs();
//        paigesRoom.assignNewJobs();
//        paigesRoom.printJobs();
    }

    public void assignNewJobs() {
        if(paigesRoom.assignNewJobs() == false) {
            System.out.println("No possible job assignments, restarting job cycle.");
            paigesRoom.forceRestartCycle();
        }
        else {
            System.out.println("Job assignment successful, here they are: ");
            paigesRoom.printJobs();
        }
    }


}
