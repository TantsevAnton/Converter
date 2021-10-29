package com.tantsev.lab2;

import android.content.res.Resources;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public interface MetricBehaviour<E extends Enum<E>> {

    default double convert(MetricBehaviour<? extends Enum<?>> dst, double amount) {
        return amount * (this.getCoef() / dst.getCoef());
    }

    default Collection<MetricBehaviour<? extends Enum<?>>> getGroupMetrics() {
        Set<MetricBehaviour<? extends Enum<?>>> result = new HashSet<>();
        for (MetricBehaviour<? extends Enum<?>> enumValue : Objects.requireNonNull(this.getClass().getEnumConstants())) {
            if (enumValue != this) {
                result.add(enumValue);
            }
        }
        return result;
    }


    double getCoef();

    String getEnding(double amount, Resources resources);

    String getName();
}
