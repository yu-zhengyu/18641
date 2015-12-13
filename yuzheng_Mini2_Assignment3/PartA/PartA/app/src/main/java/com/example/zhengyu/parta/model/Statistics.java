package com.example.zhengyu.parta.model;

import java.util.ArrayList;

/**
 * Created by zhengyu on 15/11/10.
 * This class just help user to manage all student's score, and provide function to add, delete
 * and review the information for each student.
 *
 */
public class Statistics {
    private ArrayList<SingleScore> Students;

    public Statistics() {
        Students = new ArrayList<SingleScore>();
    }

    public boolean addScore(int Stud, float[] score) {
        for (int i = 0; i < Students.size(); i++) {
            if (Students.get(i).getStud() == Stud)
                return false;
        }
        Students.add(new SingleScore(Stud, score));
        return true;
    }

    public float[] getSingleScore(int i) {
        if (i >= Students.size())
            return new float[5];
        return Students.get(i).getQ();
    }

    public ArrayList<SingleScore> getAllInfo() {
        return Students;
    }

    public float[] findLow() {
        float[] low = new float[5];
        if (Students.size() < 1)
            return low;
        low = getSingleScore(0);
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < Students.size(); i++) {
                if (Students.get(i).getQ()[j] < low[j]) {
                    low[j] = Students.get(i).getQ()[j];
                }
            }
        }
        return low;
    }

    public float[] findHigh() {
        float[] high = {0, 0, 0, 0, 0};
        if (Students.size() < 1)
            return high;
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < Students.size(); i++) {
                if(Students.get(i).getQ()[j] > high[j]) {
                    high[j] = Students.get(i).getQ()[j];
                }
            }
        }
        return high;
    }

    public float[] findAve() {
        float[] ave = new float[5];
        if (Students.size() < 1)
            return ave;
        for (int j = 0; j < 5; j++) {
            float temp = 0;
            for(int i = 0; i < Students.size(); i++) {
                temp += Students.get(i).getQ()[j];
            }
            ave[j] = temp / Students.size();
        }

        return ave;
    }

}
