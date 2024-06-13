package com.example.coconut.domain;

public class ProfanityFilter {
    private static final String[] PROFANITY_LIST = {"바보", "멍청이", "씨발", "지랄", "새끼", "병신", "쓰레기"};

    public static boolean containsProfanity(String text) {
        for (String profanity : PROFANITY_LIST) {
            if (text.contains(profanity)) {
                return true; // 비속어가 포함되어 있다면 true 반환
            }
        }
        return false; // 비속어가 포함되어 있지 않다면 false 반환
    }
}
