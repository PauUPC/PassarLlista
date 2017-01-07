package team.capcios.passarllista;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class datePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    int year;
    int day;
    int month;
    onDateChanged onDateChanged = null;

    interface onDateChanged{
        void setNewDate(int year, int month, int day);
    }

    public void datePickerSetListener(Activity listeningActivity, int year, int month, int day) {
        this.onDateChanged = (datePicker.onDateChanged) listeningActivity;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.year = year;
        this.day = day;
        this.month = month;
        onDateChanged.setNewDate(year, month, day);
    }
}
