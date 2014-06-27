package org.Mondhrez.multistatetogglebutton;

import java.util.ArrayList;
import java.util.List;

import com.example.multistatetogglebutton.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MultiStateButton extends IMultiStateButton {

	private static String TAG = "MultiStateButton";
	private List<Button> buttons;

	public MultiStateButton(Context context) {
		super(context);
		if (this.isInEditMode())
			return;

	}

	public MultiStateButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (this.isInEditMode()) {
			initThisView(attrs);
			return;
		} else
			initThisView(attrs);

	}

	private void initThisView(AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, new int[] {
				android.R.attr.entries, android.R.attr.entryValues });

		CharSequence[] texts = a.getTextArray(0);
		a.recycle();
		setButtons(texts);

	}

	public void setButtons(CharSequence[] texts) {
		// TODO: Add an exception
		if (texts == null || texts.length < 2) {
			Log.d(TAG, "Minimum quantity: 2");
			return;
		}

		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout mainLayout = (LinearLayout) inflater.inflate(
				R.layout.view_multi_state_toggle_button, this, true);
		mainLayout.removeAllViews();

		this.buttons = new ArrayList<Button>();
		for (int i = 0; i < texts.length; i++) {
			Button b = null;
			if (i == 0) {
				b = (Button) inflater.inflate(R.layout.view_left_toggle_button,
						mainLayout, false);
			} else if (i == texts.length - 1) {
				b = (Button) inflater.inflate(
						R.layout.view_right_toggle_button, mainLayout, false);
			} else {
				b = (Button) inflater.inflate(
						R.layout.view_center_toggle_button, mainLayout, false);
			}
			b.setTextAppearance(this.context, R.style.UnselectedText);
			b.setText(texts[i]);
			final int position = i;
			b.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					changeButtonsStates(position);
				}

			});
			mainLayout.addView(b);
			this.buttons.add(b);
		}
		mainLayout.setBackgroundResource(R.drawable.button_section_shape);
	}

	private boolean isEditable() {
		return this.isInEditMode();

	}

	public void setElements(List<String> texts) {
		setButtons(texts.toArray(new String[texts.size()]));
	}

	public void setElements(int arrayResourceId) {
		setButtons(this.getResources().getStringArray(arrayResourceId));
	}

	public void setButtonState(Button button, boolean selected) {
		if (button == null) {
			return;
		}
		button.setSelected(selected);
		if (selected) {
			button.setBackgroundResource(R.drawable.button_pressed);
			button.setTextAppearance(this.context, R.style.SelectedText);
		} else {
			button.setBackgroundResource(R.drawable.button_not_pressed);
			button.setTextAppearance(this.context, R.style.UnselectedText);
		}
	}

	public int getSelectedButton() {
		for (int i = 0; i < this.buttons.size(); i++) {
			if (buttons.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}

	public void changeButtonsStates(int position) {

		// set states of all button, at the same time, only one selected state
		// among buttons.
		for (int i = 0; i < this.buttons.size(); i++) {
			if (i == position) {
				setButtonState(buttons.get(i), true);
			} else {
				setButtonState(buttons.get(i), false);

			}
		}
		super.changeButtonsStates(position);
	}

}
