package com.tantsev.lab2;

import android.content.res.Resources;

public enum WeightMetric implements MetricBehaviour<WeightMetric> {
    КИЛОГРАММЫ(1D, R.plurals.kilogram_plurals),
    ФУНТЫ(0.453514739, R.plurals.lb_plurals),
    УНЦИИ(0.0283494925, R.plurals.ounce_plurals),
    СТОНЫ(6.35324015, R.plurals.groan_plurals);

    private final double coef;
    private final int resId;

    WeightMetric(double coef, int resId) {
        this.coef = coef;
        this.resId = resId;
    }

    @Override
    public double getCoef() {
        return coef;
    }

    @Override
    public String getEnding(double amount, Resources resources) {
        return resources.getQuantityString(resId, (int) amount);
    }

    @Override
    public String getName() {
        return name();
    }
}
