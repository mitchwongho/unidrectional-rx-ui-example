package com.mitchwongho.example.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.SeekBar;

import com.mitchwongho.example.R;
import com.mitchwongho.example.widget.EditTextProgressComboWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();


    @Bind(R.id.editext_progress_combo)
    EditTextProgressComboWidget comboWidget;

    private static class State {
        int progress = 0;

    }

    private State state = new State();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        comboWidget.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, String.format("onProgressChanged {progress=%d,fromUser=%B}", progress, fromUser));
                comboWidget.setEditText(Integer.valueOf(progress));
                state.progress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        comboWidget.setEditText(0);
        comboWidget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nop
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //nop
            }

            @Override
            public void afterTextChanged(Editable s) {
                final String str = s.toString();
                state.progress = TextUtils.isEmpty(str) ? 0 : new Integer(str).intValue();
                comboWidget.setSeekBarPosition(state.progress);
            }
        });
    }
}
