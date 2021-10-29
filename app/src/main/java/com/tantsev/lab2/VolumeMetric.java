package com.tantsev.lab2;

import android.content.res.Resources;

public enum VolumeMetric implements MetricBehaviour<VolumeMetric> {
    ЛИТРЫ(1D, R.plurals.litre_plurals),
    ГАЛЛОНЫ(3.78541253, R.plurals.gallon_plurals),
    КВАРТЫ(0.946351342, R.plurals.quatr_plurals),
    ПИНТЫ(0.473260767, R.plurals.pint_plurals);

    private final double coef;
    private final int resId;

    VolumeMetric(double coef, int resId) {
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
