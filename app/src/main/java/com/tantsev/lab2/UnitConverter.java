package com.tantsev.lab2;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class UnitConverter {
    private static UnitConverter instance;

    private static Pair<MetricBehaviour<? extends Enum<?>>, Double> metric;


    public static UnitConverter getInstance() {
        if (instance == null) {
            instance = new UnitConverter();
        }
        return instance;
    }

    public static Iterator<MetricBehaviour<? extends Enum<?>>> getWays(String message, Resources resources) throws NumberFormatException {
        final Pair<MetricBehaviour<? extends Enum<?>>, Double> foundMetric = findMetric(message, resources);
        if (foundMetric == null) {
            throw new NumberFormatException();
        }
        metric = foundMetric;
        return foundMetric.first.getGroupMetrics().iterator();
    }

    @SuppressLint("DefaultLocale")
    public static String convert(MetricBehaviour<? extends Enum<?>> dstMetric, Resources resources) throws NumberFormatException {
        if (metric == null || dstMetric == null) {
            throw new NumberFormatException();
        }
        final Double srcMetricAmount = metric.second;
        final MetricBehaviour<? extends Enum<?>> srcMetric = metric.first;
        final double convertResult = srcMetric.convert(dstMetric, srcMetricAmount);
        return String.format("%s %s это %.5f %s", srcMetricAmount, srcMetric.getEnding(srcMetricAmount, resources), convertResult, dstMetric.getEnding(convertResult, resources));
    }

    private static Pair<MetricBehaviour<? extends Enum<?>>, Double> findMetric(String message, Resources resources) throws NumberFormatException {
        final String[] strings = message.split(" ");
        if (strings.length != 2) {
            throw new NumberFormatException();
        }
        final double amount = Double.parseDouble(strings[0]);
        final String metricFromMessage = strings[1];

        final List<MetricBehaviour<? extends Enum<?>>> metrics = new ArrayList<>(Arrays.asList(LengthMetric.values()));
        metrics.addAll(Arrays.asList(WeightMetric.values()));
        metrics.addAll(Arrays.asList(VolumeMetric.values()));

        for (MetricBehaviour<? extends Enum<?>> metric : metrics) {
            if (metricFromMessage.equals(metric.getEnding(amount, resources))) {
                return new Pair<>(metric, amount);
            }
        }
        return null;
    }
}