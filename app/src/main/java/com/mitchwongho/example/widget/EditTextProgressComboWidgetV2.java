package com.mitchwongho.example.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.mitchwongho.example.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class EditTextProgressComboWidgetV2 extends FrameLayout {

    @Bind(R.id.progress_edit)
    EditText editText;
    @Bind(R.id.progress_seek)
    SeekBar seekBar;

    public interface OnWidgetPositionChangeListener {
        void onPositionChanged(Integer position);
    }

    private EditTextProgressComboWidgetV2.OnWidgetPositionChangeListener listener = null;

    public EditTextProgressComboWidgetV2(Context context) {
        this(context, null);
    }

    public EditTextProgressComboWidgetV2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextProgressComboWidgetV2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context);
    }

    @TargetApi(23)
    public EditTextProgressComboWidgetV2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor(context);
    }

    private void constructor(@NonNull final Context context) {
        LayoutInflater.from(context).inflate(R.layout.edittext_progress_combo_widget, this);
        ButterKnife.bind(this);
        editText.setText(Integer.toString(0));

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final String str = s.toString();
                final int i = TextUtils.isEmpty(str) ? 0 : Integer.valueOf(str);
                seekBar.setProgress( Math.min(seekBar.getMax(), i));
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("", "setOnSeekBarChangeListener");
                editText.setText(Integer.toString(progress));
                if (listener != null) {
                    listener.onPositionChanged(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setOnWidgetPositionChangeListener(@NonNull final EditTextProgressComboWidgetV2.OnWidgetPositionChangeListener listener) {

    }
}
