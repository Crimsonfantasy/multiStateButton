package org.Mondhrez.multistatetogglebutton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class IMultiStateButton extends LinearLayout {
	public interface OnValueChangedListener {
		public void onValueChanged(int value);
	}

	OnValueChangedListener listener;
	Context context;

	public IMultiStateButton(Context context) {
		super(context, null);
		this.context = context;
	}

	public IMultiStateButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public void setOnValueChangedListener(OnValueChangedListener l) {
		this.listener = l;
	}

	public void changeButtonsStates(int value) {
		if (this.listener != null) {
			listener.onValueChanged(value);
		}
	}
}
