package com.mitchwongho.example.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.SubscriptSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.jakewharton.rxbinding.widget.RxSeekBar;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.SeekBarChangeEvent;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import com.mitchwongho.example.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func2;
import rx.subjects.ReplaySubject;
import rx.subscriptions.CompositeSubscription;

/**
 *
 */
public class EditTextProgressComboWidgetV3 extends FrameLayout {

    public final static String TAG = EditTextProgressComboWidgetV3.class.getSimpleName();

    @Bind(R.id.progress_edit)
    EditText editText;
    @Bind(R.id.progress_seek)
    SeekBar seekBar;

    private CompositeSubscription compositeSubscription;

    private ReplaySubject<EditTextProgressComboWidgetEvent> sink;
    private ReplaySubject<Integer> sources;

    public EditTextProgressComboWidgetV3(Context context) {
        this(context, null);
    }

    public EditTextProgressComboWidgetV3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextProgressComboWidgetV3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context);
    }

    @TargetApi(23)
    public EditTextProgressComboWidgetV3(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor(context);
    }

    private void constructor(@NonNull final Context context) {
        LayoutInflater.from(context).inflate(R.layout.edittext_progress_combo_widget, this);
        ButterKnife.bind(this);
        sink = ReplaySubject.create(1);
        sources = ReplaySubject.create(1);
        compositeSubscription = new CompositeSubscription();
        editText.setText(Integer.toString(0));

        final Observable<EditTextProgressComboWidgetEvent> obzEditText = RxTextView.
                afterTextChangeEvents(editText).
                map(textViewAfterTextChangeEvent -> {
                    final Editable s = textViewAfterTextChangeEvent.editable();
                    final String str = s.toString();
                    final int i = TextUtils.isEmpty(str) ? 0 : Integer.valueOf(str);
                    seekBar.setProgress(Math.min(seekBar.getMax(), i));
                    return new EditTextProgressComboWidgetEvent(i);
                });

        final Observable<EditTextProgressComboWidgetEvent> obzSeekBr = RxSeekBar.changes(seekBar).
                map(integer -> {
                    Log.d(TAG, String.format("seekBar.changes {value=%d}", integer));
                    editText.setText(Integer.toString(integer));
                    return new EditTextProgressComboWidgetEvent(integer);
                });

        final Subscription sub = Observable.merge(obzEditText, obzSeekBr).
                startWith(new EditTextProgressComboWidgetEvent(0)).
                scan((acc, event) -> event ).
                subscribe(sink);

        compositeSubscription.add(sub);
    }

    public Observable<EditTextProgressComboWidgetEvent> getSink() {
        return sink.asObservable();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        compositeSubscription.clear();
        compositeSubscription.unsubscribe();
    }

    public static class EditTextProgressComboWidgetEvent implements WidgetEvent {
        private Integer value;

        public EditTextProgressComboWidgetEvent(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
