import java.util.ArrayList;
import java.util.Scanner;

class ProcessPreemptive {
    int pid;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    ProcessPreemptive(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class SJFSchedulingPreemptive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ProcessPreemptive> processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processes.add(new ProcessPreemptive(i + 1, arrivalTime, burstTime));
        }

        int time = 0, completed = 0;
        ProcessPreemptive currentProcess = null;

        while (completed < n) {
            ProcessPreemptive shortestJob = null;
            for (ProcessPreemptive process : processes) {
                if (process.arrivalTime <= time && process.remainingTime > 0) {
                    if (shortestJob == null || process.remainingTime < shortestJob.remainingTime) {
                        shortestJob = process;
                    }
                }
            }

            if (shortestJob == null) {
                time++;
                continue;
            }

            shortestJob.remainingTime--;
            time++;

            if (shortestJob.remainingTime == 0) {
                completed++;
                shortestJob.completionTime = time;
                shortestJob.turnaroundTime = shortestJob.completionTime - shortestJob.arrivalTime;
                shortestJob.waitingTime = shortestJob.turnaroundTime - shortestJob.burstTime;
            }
        }

        System.out.println("\nPreemptive SJF Scheduling:");
        System.out.printf("%-10s %-15s %-10s %-15s %-15s %-15s%n", 
                          "Process", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time", "Waiting Time");

        for (ProcessPreemptive process : processes) {
            System.out.printf("%-10d %-15d %-10d %-15d %-15d %-15d%n", 
                              process.pid, process.arrivalTime, process.burstTime, process.completionTime, 
                              process.turnaroundTime, process.waitingTime);
        }

        scanner.close();
    }
}
