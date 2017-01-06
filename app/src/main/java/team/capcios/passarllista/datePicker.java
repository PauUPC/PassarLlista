package team.capcios.passarllista;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class datePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    int pYear;
    int pDay;
    int pMonth;
    onDateChanged onDateChanged = null;

    interface onDateChanged{
        void setNewDate(int year, int month, int day);
    }

    public void datePickerSetListener(Activity listeningActivity) {
        this.onDateChanged = (datePicker.onDateChanged) listeningActivity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        pYear = year;
        pDay = day;
        pMonth = month;
        onDateChanged.setNewDate(year, month, day);
    }
}
