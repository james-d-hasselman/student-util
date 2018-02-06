package org.hasselman.util;

import org.junit.Test;
import org.junit.Assert;

public class StudentUtilTest {
    @Test
    public void testGraduationDateToday() {
        Assert.assertEquals("Grade level is 12 when graduation date is today.", 12, 
                            getGradeLevel("2016-06-02", "2016-06-02"));
    }

    @Test
    public void testPreSchool() {
        Assert.assertEquals("Grade level is pre-school.", -1,
                            getGradeLevel("2016-06-02", "2000-06-02"));
    }

    @Test
    public void testPostHighSchool() {
        Assert.assertEquals("Grade level is post-high-school.", 99,
                            getGradeLevel("2016-06-02", "2016-06-03"));
    }

    @Test
    public void testNullGivenDate() {
        Assert.assertEquals("Gradle level is post-high-school when a null " + 
                            "given date is used.", 99, 
                            getGradeLevel("2016-06-02", null));
    }

    @Test
    public void testHighSchoolJunior() {
        Assert.assertEquals("Grade level is 11", 11, 
                            getGradeLevel("2016-06-02", "2015-06-02"));
    }

    @Test
    public void testGradleLevelSecondHalf() {
        Assert.assertEquals("Grade level is 12 in March.", 12, 
                            getGradeLevel("2016-06-02", "2016-03-15"));
    }

    @Test
    public void testGradeLevelFirstHalf() {
        Assert.assertEquals("Grade level is 12 in October.", 12,
                            getGradeLevel("2016-06-02", "2015-10-31"));
    }

    @Test
    public void testRespectsSchoolEndDate() {
        Assert.assertEquals("Grade level is 11.", 11,
                            getGradeLevel("2016-06-02", "2015-06-03"));
    }

    @Test
    public void testGradeLevel10() {
        Assert.assertEquals("Grade level is 10.", 10,
                            getGradeLevel("2016-06-02", "2014-06-29"));
    }

    @Test
    public void testGradeLevel10OnLastDay() {
        Assert.assertEquals("Grade level is 10 on the last day of school.", 10, 
                            getGradeLevel("2016-06-02", "2014-06-30"));
    }

    @Test
    public void testGradeLevel9() {
        Assert.assertEquals("Grade level is 9.", 9, 
                            getGradeLevel("2016-06-02", "2013-06-30"));
    }

    @Test
    public void testGradeLevel8() {
        Assert.assertEquals("Grade level is 8.", 8,
                            getGradeLevel("2016-06-02", "2012-06-30"));
    }

    @Test
    public void testGradeLevel7() {
        Assert.assertEquals("Grade level is 7.", 7,
                            getGradeLevel("2016-06-02", "2011-06-30"));
    }

    @Test
    public void testGradeLevel6() {
        Assert.assertEquals("Grade level is 6.", 6,
                            getGradeLevel("2016-06-02", "2010-06-30"));
    }

    @Test
    public void testGradeLevel5() {
        Assert.assertEquals("Grade level is 5.", 5,
                            getGradeLevel("2016-06-02", "2009-06-30"));
    }

    @Test
    public void testGradeLevel4() {
        Assert.assertEquals("Grade level is 4.", 4,
                            getGradeLevel("2016-06-02", "2008-06-30"));
    }

    @Test
    public void testGradeLevel3() {
        Assert.assertEquals("Grade level is 3.", 3,
                            getGradeLevel("2016-06-02", "2007-06-30"));
    }

    @Test
    public void testGradeLevel2() {
        Assert.assertEquals("Grade level is 2.", 2,
                            getGradeLevel("2016-06-02", "2006-06-30"));
    }

    @Test
    public void testGradeLevel1() {
        Assert.assertEquals("Grade level is 1.", 1,
                            getGradeLevel("2016-06-02", "2005-06-30"));
    }

    @Test
    public void testGradeLevelKindergarten() {
        Assert.assertEquals("Grade level is Kindergarten.", 0, 
                            getGradeLevel("2016-06-02", "2004-06-30"));
    }

    @Test
    public void testGradleLevel9InAugust() {
        Assert.assertEquals("Grade level is 9 in August.", 9,
                            getGradeLevel("2016-06-30", "2012-08-12"));
    }

    @Test
    public void testEarlyGraduation() {
        Assert.assertEquals("Graduating in December with post graduation given date", 99, 
                            getGradeLevel("2015-12-20", "2016-01-01"));
    }

    @Test
    public void testEaryGraduationPreGraduation() {
        Assert.assertEquals("Graduating in December with pre graduation given date", 12, 
                            getGradeLevel("2015-12-20", "2015-10-31"));
    }
    private int getGradeLevel(String graduationDate, String givenDate) {
        StudentUtil util = new StudentUtil();
        return util.getGradeOnGivenDate(graduationDate, givenDate);
    }
}

