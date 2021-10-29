package com.tantsev.lab2;

import android.content.res.Resources;

public enum LengthMetric implements MetricBehaviour<LengthMetric> {
    МЕТРЫ(1D, R.plurals.meter_plurals),
    ДЮЙМЫ(0.0254000508, R.plurals.inch_plurals),
    ФУТЫ(0.304785126, R.plurals.ft_plurals),
    ЯРДЫ(0.914076782, R.plurals.yard_plurals);

    private final double coef;
    private final int resId;

    LengthMetric(double coef, int resId) {
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
