package main;

import calendar.Event;
import calendar.MyCalendar;
import calendar.RecurringEvent;
import guiComponents.CalendarFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SimpleCalendarTester {
    static MyCalendar myCalendar = new MyCalendar();
    public static void main(String[] args) {
        if (readFile()) {
            System.out.println("Loading is done!");
        }

        CalendarFrame frame = new CalendarFrame(myCalendar);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (saveFile()) {
                    System.out.println("Events saved to output.txt");
                }
            }
        });
        frame.setVisible(true);
    }
    /**
     * Reads events.txt to load saved events onto the calendar.
     * @return  true if the file was found and read successfully and false if it failed
     */
    public static boolean readFile() {
        try {
            Scanner scannerTextFile = new Scanner(new File("events.txt"));
            while (scannerTextFile.hasNextLine()) {
                String name = scannerTextFile.nextLine();

                String information = scannerTextFile.nextLine();

                information = information.toUpperCase();

                DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yy");
                DateTimeFormatter hourMinute = DateTimeFormatter.ofPattern("H:m");

                String[] splitInfo = information.split(" ");
                if (splitInfo[0].contains("S") || splitInfo[0].contains("M") || splitInfo[0].contains("T") || splitInfo[0].contains("W")
                        || splitInfo[0].contains("R") || splitInfo[0].contains("F") || splitInfo[0].contains("A") ) {

                    LocalTime startTime = LocalTime.parse(splitInfo[1],hourMinute);
                    LocalTime endTime = LocalTime.parse(splitInfo[2], hourMinute);

                    LocalDate startDate = LocalDate.parse(splitInfo[3], monthDayYear);
                    LocalDate endDate = LocalDate.parse(splitInfo[4], monthDayYear);
                    RecurringEvent event = new RecurringEvent(name, splitInfo[0], startDate, endDate, startTime, endTime);
                    myCalendar.add(event);
                }
                else {
                    LocalDate date = LocalDate.parse(splitInfo[0], monthDayYear);
                    LocalTime startTime = LocalTime.parse(splitInfo[1], hourMinute);
                    LocalTime endTime = LocalTime.parse(splitInfo[2], hourMinute);
                    Event event = new Event(name, date, startTime, endTime);
                    myCalendar.add(event);
                }

            }
            scannerTextFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return true;
    }

    /**
     * Saves an output file called output.txt
     * @return  true if it was successful and false if it wasn't
     */
    public static boolean saveFile() {
        try (FileWriter fileWriter = new FileWriter("events.txt")) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (RecurringEvent e: myCalendar.getRecurringEventsList()) {
                bufferedWriter.write(e.inFormatMonthDayYear());
            }
            for (Event e: myCalendar.getOneTimeEventsList()) {
                bufferedWriter.write(e.inFormatMonthDayYear());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
