package com.mitchwongho.example.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
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
public class EditTextProgressComboWidget extends FrameLayout {

    @Bind(R.id.progress_edit)
    EditText editText;
    @Bind(R.id.progress_seek)
    SeekBar seekBar;

    public EditTextProgressComboWidget(Context context) {
        this(context, null);
    }

    public EditTextProgressComboWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextProgressComboWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context);
    }

    @TargetApi(23)
    public EditTextProgressComboWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor(context);
    }

    private void constructor(@NonNull final Context context) {
        LayoutInflater.from(context).inflate(R.layout.edittext_progress_combo_widget, this);
        ButterKnife.bind(this);
    }

    public void setEditText(Integer value) {
        editText.setText(value.toString());
    }

    public void addTextChangedListener(@NonNull final TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    public void setSeekBarPosition(@NonNull final Integer value) {
        seekBar.setProgress(value);
    }

    public void setOnSeekBarChangeListener(@NonNull final SeekBar.OnSeekBarChangeListener listener) {
        seekBar.setOnSeekBarChangeListener(listener);
    }
}
