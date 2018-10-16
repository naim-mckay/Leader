package com.naim.cube.leaderboard.util;

public class Utils {

    public static boolean isNumber(String inStr) {
        try {
            int d = Integer.parseInt(inStr);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }



    public static int getNumber(String inStr){
        int outInt = -1;
        try {
            outInt = Integer.parseInt(inStr);
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.println("Error retriving number "+inStr+" Error is "+nfe.getMessage());
        }
        return outInt;

    }
}
