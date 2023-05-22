package ru.borisov.phrase.domain.constant;

public class RegExp {

    public final static String NICKNAME = "^[a-zA-Z0-9а-яА-Я. _-]{4,15}$";
    public final static String PASSWORD = "^[a-zA-Z0-9а-яА-Я.,:; _?!+=/'\\\\\"*(){}\\[\\]\\-]{8,100}$";
    public final static String PHRASE = "^[a-zA-Z0-9а-яА-Я.,:; _?!+=/'\\\\\"*(){}\\[\\]\\-]{1,140}$";
    public final static String TAG = "^[a-zA-Z0-9а-яА-Я.,:; _?!+=/'\\\"*(){}\\[\\]\\-]{3,25}$";
}
