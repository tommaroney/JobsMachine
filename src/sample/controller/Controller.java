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

/*        try (ObjectOutputStream objOStrm = new ObjectOutputStream(new FileOutputStream(paigesRoom.getClassroomName())) )
        {
            objOStrm.writeObject(paigesRoom);
        } catch(IOException ioe) {
            System.out.println("Exception during serialization: " + ioe);
        }*/
    }

    public Controller(String fileName) {
        try ( ObjectInputStream objIStrm = new ObjectInputStream(new FileInputStream(fileName)) )
        {
            paigesRoom2 = (Classroom)objIStrm.readObject();

        } catch(Exception e) {
            System.out.println("Exception during deserialization: " + e);
        }
            paigesRoom2.printPriorJobs();
            paigesRoom2.assignNewJobs();
            paigesRoom2.printJobs();
    }





    public void assignNewJobs() {
        if(!paigesRoom.assignNewJobs()) {
            System.out.println("No possible job assignments, restarting job cycle.");
            paigesRoom.forceRestartCycle();
        }
        else {
            System.out.println("Job assignment successful, here they are: ");
            paigesRoom.printJobs();
        }
    }


}
