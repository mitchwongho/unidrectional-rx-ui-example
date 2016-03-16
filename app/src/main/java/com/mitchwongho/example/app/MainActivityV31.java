package com.mitchwongho.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mitchwongho.example.R;
import com.mitchwongho.example.widget.EditTextProgressComboWidgetV3;
import com.mitchwongho.example.widget.EditTextProgressComboWidgetV31;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivityV31 extends AppCompatActivity {

    public final static String TAG = MainActivityV31.class.getSimpleName();


    @Bind(R.id.editext_progress_combo)
    EditTextProgressComboWidgetV31 comboWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v3_1);
        ButterKnife.bind(this);
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        comboWidget.getSink().subscribe(
                editTextProgressComboWidgetEvent -> {
                    //onNext
                    Log.d(TAG, String.format("comboWidget::editTextProgressComboWidgetEvent {value=%d}", editTextProgressComboWidgetEvent.getValue()));
                }, throwable -> {
                    //onError
                }, () -> {
                    //onCompleted
                });

        final Observable<Integer> obzInterval =
                Observable.<Integer>interval(1300, TimeUnit.MILLISECONDS).filter(tick -> (tick < 100) ).
                map( aLong -> aLong.intValue());

        comboWidget.setSources(obzInterval);

    }

    private static class State {
        int progress = 0;

    }
}
