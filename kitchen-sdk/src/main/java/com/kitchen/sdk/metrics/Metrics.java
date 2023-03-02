package com.kitchen.sdk.metrics;

public class Metrics {
    public static Cur Cur(String key1, String key2, String key3, String environment) {
        return new Cur(key1, key2, key3, environment);
    }

    public static Cur Cur(String key1, String key2, String key3) {
        return new Cur(key1, key2, key3);
    }

    public static Cur Cur(String key1, String key2) {
        return Cur(key1, key2, "");
    }

    public static Cur Cur(String key1) {
        return Cur(key1, "", "");
    }

    public static RT RT(String key1, String key2, String key3, String environment) {
        return new RT(key1, key2, key3);
    }

    public static RT RT(String key1, String key2, String key3) {
        return new RT(key1, key2, key3);
    }

    public static RT RT(String key1, String key2) {
        return RT(key1, key2, "");
    }

    public static RT RT(String key1) {
        return RT(key1, "", "");
    }

    public static QPS QPS(String key1, String key2, String key3, String environment) {
        return new QPS(key1, key2, key3, environment);
    }

    public static QPS QPS(String key1, String key2, String key3) {
        return new QPS(key1, key2, key3);
    }

    public static QPS QPS(String key1, String key2) {
        return QPS(key1, key2, "");
    }

    public static QPS QPS(String key1) {
        return QPS(key1, "", "");
    }

    public static FailRate FR(String key1, String key2, String key3, String environment) {
        return new FailRate(key1, key2, key3, environment);
    }

    public static FailRate FR(String key1, String key2, String key3) {
        return new FailRate(key1, key2, key3);
    }

    public static FailRate FR(String key1, String key2) {
        return FR(key1, key2, "");
    }

    public static FailRate FR(String key1) {
        return FR(key1, "", "");
    }

    public static SuccessRate SR(String key1, String key2, String key3, String environment) {
        return new SuccessRate(key1, key2, key3, environment);
    }

    public static SuccessRate SR(String key1, String key2, String key3) {
        return new SuccessRate(key1, key2, key3);
    }

    public static SuccessRate SR(String key1, String key2) {
        return SR(key1, key2, "");
    }

    public static SuccessRate SR(String key1) {
        return SR(key1, "", "");
    }

    public static Hit HIT(String key1, String key2, String key3, String environment) {
        return new Hit(key1, key2, key3, environment);
    }

    public static Hit HIT(String key1, String key2, String key3) {
        return new Hit(key1, key2, key3);
    }

    public static Hit HIT(String key1, String key2) {
        return HIT(key1, key2, "");
    }

    public static Hit HIT(String key1) {
        return HIT(key1, "", "");
    }

    public static Common CM(String key1, String key2, String key3, String environment) {
        return new Common(key1, key2, key3, environment);
    }

    public static Common CM(String key1, String key2, String key3) {
        return new Common(key1, key2, key3);
    }

    public static Common CM(String key1, String key2) {
        return CM(key1, key2, "");
    }

    public static Common CM(String key1) {
        return CM(key1, "", "");
    }
}