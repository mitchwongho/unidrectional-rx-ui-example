package com.mitchwongho.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mitchwongho.example.R;
import com.mitchwongho.example.widget.EditTextProgressComboWidgetV2;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityV21 extends AppCompatActivity {

    public final static String TAG = MainActivityV21.class.getSimpleName();


    @Bind(R.id.editext_progress_combo_1)
    EditTextProgressComboWidgetV2 comboWidget1;
    @Bind(R.id.editext_progress_combo_2)
    EditTextProgressComboWidgetV2 comboWidget2;
    private State state = new State();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
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
        comboWidget1.setOnWidgetPositionChangeListener(new EditTextProgressComboWidgetV2.OnWidgetPositionChangeListener() {
            @Override
            public void onPositionChanged(Integer position) {
                state.progress1 = position.intValue();
            }
        });
        comboWidget2.setOnWidgetPositionChangeListener(new EditTextProgressComboWidgetV2.OnWidgetPositionChangeListener() {
            @Override
            public void onPositionChanged(Integer position) {
                state.progress2 = position.intValue();
            }
        });
    }

    private static class State {
        int progress1 = 0;
        int progress2 = 0;

    }
}
