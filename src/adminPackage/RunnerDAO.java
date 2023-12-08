package adminPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RunnerDAO {
    private static final String FILE_PATH = ("programData\\Runner.txt");

    public void addRunner(Runner runner) throws IOException {
   
    List<Runner> existingRunners = getAllRunners();


    for (Runner existingRunner : existingRunners) {
        if (existingRunner.getUsername().equalsIgnoreCase(runner.getUsername())) {
            throw new IllegalArgumentException("Username must be unique.");
        }
    }

    try (FileWriter fw = new FileWriter(FILE_PATH, true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {
        out.println(runner.getUsername() + ";" + runner.getPassword() + ";" + runner.getPhoneNumber());
    }
}



    public List<Runner> getAllRunners() throws IOException {
        File file = new File(FILE_PATH);
        List<Runner> runners = new ArrayList<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Runner runner = parseRunner(line);
                    if (runner != null) {
                        runners.add(runner);
                    }
                }
            }
        }
        return runners;
    }

    public void updateRunner(Runner runnerToUpdate) throws IOException {
        List<Runner> runners = getAllRunners();
        for (int i = 0; i < runners.size(); i++) {
            Runner runner = runners.get(i);
            if (runner.getUsername().equals(runnerToUpdate.getUsername())) {
                runners.set(i, runnerToUpdate);
                break;
            }
        }
        saveAllRunners(runners);
    }

    public void deleteRunner(String username) throws IOException {
        List<Runner> runners = getAllRunners();
        runners.removeIf(runner -> runner.getUsername().equals(username));
        saveAllRunners(runners);
    }

    private void saveAllRunners(List<Runner> runners) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Runner runner : runners) {
                out.println(runner.getUsername() + ";" + runner.getPassword() + ";" + runner.getPhoneNumber());
            }
        }
    }

    private Runner parseRunner(String line) {
        String[] parts = line.split(";");
        if (parts.length < 3) return null; 
        return new Runner(parts[0], parts[1], parts[2]); 
    }
}
