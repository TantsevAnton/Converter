package com.tantsev.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final Map<Button, MetricBehaviour<? extends Enum<?>>> buttonToMetric = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText converterEditText = findViewById(R.id.converterEditText);
        Button firstWay = findViewById(R.id.firstWay);
        Button secondWay = findViewById(R.id.secondWay);
        Button thirdWay = findViewById(R.id.thirdWay);
        TextView result = findViewById(R.id.result);
        TextView hint = findViewById(R.id.hint);
        hint.setText(this.getResources().getText(R.string.hint_text));
        converterEditText.setOnKeyListener((view, keyCode, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                result.setVisibility(View.GONE);
                final String message = converterEditText.getText().toString();
                try {
                    final Iterator<MetricBehaviour<? extends Enum<?>>> waysByMessage = UnitConverter.getInstance().getWays(message, this.getResources());
                    setWayToButton(firstWay, waysByMessage);
                    setWayToButton(secondWay, waysByMessage);
                    setWayToButton(thirdWay, waysByMessage);
                } catch (NumberFormatException e) {
                    showError(result, hint);
                }
                return true;
            }
            return false;
        });
        @SuppressLint("NonConstantResourceId") View.OnClickListener onClickListener = view -> {
            MetricBehaviour<? extends Enum<?>> dstMetric = buttonToMetric.get((Button) findViewById(view.getId()));
            hint.setVisibility(View.GONE);
            result.setVisibility(View.VISIBLE);
            try {
                result.setText(UnitConverter.getInstance().convert(dstMetric, getResources()));
            } catch (NumberFormatException e) {
                showError(result, hint);
            }
        };
        firstWay.setOnClickListener(onClickListener);
        secondWay.setOnClickListener(onClickListener);
        thirdWay.setOnClickListener(onClickListener);
    }

    private void setWayToButton(Button firstWay, Iterator<MetricBehaviour<? extends Enum<?>>> waysByMessage) {
        final MetricBehaviour<? extends Enum<?>> firstWayConverter = waysByMessage.next();
        buttonToMetric.put(firstWay, firstWayConverter);
        firstWay.setText(firstWayConverter.getName());
    }

    private void showError(TextView result, TextView hint) {
        result.setVisibility(View.VISIBLE);
        result.setText("ОШИБКА");
        hint.setVisibility(View.VISIBLE);
        hint.setText(this.getResources().getText(R.string.hint_text));
    }
}