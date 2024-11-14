import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class SJFSchedulingNonPreemptive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }

        // Sort by Arrival Time first, then Burst Time
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime)
                                  .thenComparingInt(p -> p.burstTime));

        int currentTime = 0;
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }
            process.completionTime = currentTime + process.burstTime;
            currentTime += process.burstTime;
            process.turnaroundTime = process.completionTime - process.arrivalTime;
            process.waitingTime = process.turnaroundTime - process.burstTime;
        }

        System.out.println("\nNon-Preemptive SJF Scheduling:");
        System.out.printf("%-10s %-15s %-10s %-15s %-15s %-15s%n", 
                          "Process", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time", "Waiting Time");

        for (Process process : processes) {
            System.out.printf("%-10d %-15d %-10d %-15d %-15d %-15d%n", 
                              process.pid, process.arrivalTime, process.burstTime, process.completionTime, 
                              process.turnaroundTime, process.waitingTime);
        }

        scanner.close();
    }
}
