package com.mitchwongho.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mitchwongho.example.R;
import com.mitchwongho.example.widget.EditTextProgressComboWidgetV3;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityV3 extends AppCompatActivity {

    public final static String TAG = MainActivityV3.class.getSimpleName();


    @Bind(R.id.editext_progress_combo)
    EditTextProgressComboWidgetV3 comboWidget;
    private State state = new State();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v3);
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
    }

    private static class State {
        int progress = 0;

    }
}
