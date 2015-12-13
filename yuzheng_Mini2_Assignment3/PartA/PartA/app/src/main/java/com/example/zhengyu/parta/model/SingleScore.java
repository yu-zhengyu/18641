package com.example.zhengyu.parta.model;

/**
 * Created by zhengyu on 15/11/10.
 * This class represent a student's information include his student id and five times score
 */
public class SingleScore {
    private int Stud;
    private float[] Q;

    public SingleScore() {

    }

    public SingleScore(int stud, float[] q) {
        Stud = stud;
        Q = q;
    }

    public int getStud() {
        return Stud;
    }

    public void setStud(int stud) {
        Stud = stud;
    }

    public float[] getQ() {
        return Q;
    }

    public void setQ(float[] q) {
        Q = q;
    }
}
