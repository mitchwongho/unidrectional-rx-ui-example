package com.mitchwongho.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mitchwongho.example.R;
import com.mitchwongho.example.widget.EditTextProgressComboWidgetV2;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityV2 extends AppCompatActivity {

    public final static String TAG = MainActivityV2.class.getSimpleName();


    @Bind(R.id.editext_progress_combo)
    EditTextProgressComboWidgetV2 comboWidget;
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
        comboWidget.setOnWidgetPositionChangeListener(new EditTextProgressComboWidgetV2.OnWidgetPositionChangeListener() {
            @Override
            public void onPositionChanged(Integer position) {
                state.progress = position.intValue();
            }
        });
    }

    private static class State {
        int progress = 0;

    }
}
