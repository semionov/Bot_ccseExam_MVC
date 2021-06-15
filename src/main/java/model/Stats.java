package model;

public class Stats {

    private long exams;
    private long tasks;
    private long info;
    private String statsMessage;



    public Stats() {
        this.exams = 0;
        this.tasks = 0;
        this.info = 0;
    }

    public long getExams() {
        return exams;
    }

    public long getTask() {
        return tasks;
    }

    public long getInfo() {
        return info;
    }

    public void plusInfo() {
        info++;
    }

    public void plusExam() {
        exams++;
    }

    public void plusTask() {
        tasks++;
    }

    public String getStatsMessage() {
        statsMessage = "About me: " + info + "\n" +
            "Exams: " + exams  + "\n" +
            "Tasks: " + tasks + "\n" +
            "Exams and tasks: " + (tasks + exams);
        return statsMessage;
    }
}
