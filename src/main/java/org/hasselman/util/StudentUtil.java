package org.hasselman.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.Month;

/**
 * Given a graduation date and a query date, returns the grade level of the 
 * student on the query date.
 *
 * @author James D. Hasselman
 */
public class StudentUtil {
    /**
     * Returns the current grade level of a student based on graduation date 
     * and a query date.
     *
     * @param highSchoolGradDate Date the student is graduating from High School
     * @param givenDate Date used to determine the student's current grade level
     * @return A postitive integer 1-12 representing the grade levels 1-12, 0 for Kindergarten, -1 for Pre-School, or 99 for Post-High-School.
     */
    public int getGradeOnGivenDate(String highSchoolGradDate, String givenDate) {
        //return originalImplementation(highSchoolGradDate, givenDate);
        return secondImplementation(highSchoolGradDate, givenDate);
    }

    // second more compact solution which is clearer and takes better advantage of
    // the new Java 8 time package.
    private int secondImplementation(String highSchoolGradDate, String givenDate) {
        // handle the easy case
        if (highSchoolGradDate.equals(givenDate)) {
            return 12;
        }

        // perform setup for more complicated cases
        LocalDate graduationDate = LocalDate.parse(highSchoolGradDate);
        LocalDate queryDate;
        if(givenDate == null) {
            queryDate = LocalDate.now();
        } else {
            queryDate = LocalDate.parse(givenDate);
        }

        if(queryDate.isAfter(graduationDate)) {
            return 99;
        } 

        LocalDate endSchoolYear = getEndSchoolYear(graduationDate);
        LocalDate endQuerySchoolYear = getEndSchoolYear(queryDate);
        // we don't need to worry about the query date coming after the 
        // graduation date since that case is already handled by this point
        Period timespan = Period.between(endQuerySchoolYear, endSchoolYear);

        int rawGradeLevel = 12 - timespan.getYears();
        if(rawGradeLevel >= 0) {
            return rawGradeLevel;
        }
        
        return -1;
    }

    // first solution
    private int originalImplementation(String highSchoolGradDate, String givenDate) {
        // handle the easy case
        if (highSchoolGradDate.equals(givenDate)) {
            return 12;
        }

        // perform setup for more complicated cases
        LocalDate graduationDate = LocalDate.parse(highSchoolGradDate);
        LocalDate queryDate;
        if(givenDate == null) {
            queryDate = LocalDate.now();
        } else {
            queryDate = LocalDate.parse(givenDate);
        }

        // handle next easy case
        if(queryDate.isAfter(graduationDate)) {
            return 99;
        } 
       
        // handle K - 12
        LocalDate endSchoolYear = getEndSchoolYear(graduationDate);
        LocalDate endPreviousSchoolYear = endSchoolYear.withYear(endSchoolYear.getYear() - 1);
	for(int gradeLevel = 12; gradeLevel >= 0; gradeLevel--) {
            if(queryDate.compareTo(endSchoolYear) <= 0 && 
               queryDate.compareTo(endPreviousSchoolYear) > 0) {
                return gradeLevel;
            }

            endSchoolYear = endPreviousSchoolYear;
            endPreviousSchoolYear = endSchoolYear.withYear(endSchoolYear.getYear() - 1);
        }
 
        // any further back in time and it's pre-school
        return -1;
    }

    private LocalDate getEndSchoolYear(LocalDate date) {
        if(date.getMonth().compareTo(Month.JANUARY) >= 0 && 
           date.getMonth().compareTo(Month.JUNE) <= 0) {
            return LocalDate.of(date.getYear(), Month.JUNE, 30);
        } else {
            return LocalDate.of(date.getYear() + 1, Month.JUNE, 30);
        }
    }
}

