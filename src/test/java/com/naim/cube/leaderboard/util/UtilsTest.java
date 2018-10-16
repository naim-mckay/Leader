package com.naim.cube.leaderboard.util;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


public class UtilsTest {

    @Test
    public void testiIsNumber_withNonNumber_should_returnFalse() {
        boolean result = Utils.isNumber("nonNumber");
        assertThat(result, is(false));
    }

    @Test
    public void testiIsNumber_withStringNumber_should_returnTrue() {
        boolean result = Utils.isNumber("23");
        assertThat(result, is(true));
    }


    @Test
    public void testGetNumber_withNonNumber_should_returnMinusOne(){
        int result = Utils.getNumber("nonNumber");
        assertThat(result, is(-1));
    }

    @Test
    public void testGetNumber_withNumberNumber_should_returnGivernNumber(){
        int result = Utils.getNumber("5");
        assertThat(result, is(5));
    }
}
