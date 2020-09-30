package com.ldh.onthidaihoc.collections;

import com.ldh.onthidaihoc.model.User;

import java.util.Comparator;

public class ScoreComparator implements Comparator<User> {
    @Override
    public int compare(User user, User t1) {
        int score1 = Integer.parseInt(user.getScore());
        int score2 = Integer.parseInt(t1.getScore());

        if (score1 == score2) {
            return 0;
        } else if (score1 < score2) {
            return 1;
        } else {
            return -1;
        }
    }
}
