package team.capcios.passarllista.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import team.capcios.passarllista.R;
import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Dia;

public class AssignaturaListCursorAdapter extends CursorAdapter {

    private Dia dia;

    public AssignaturaListCursorAdapter(Context context, Cursor cursor, Dia dia) {
        super(context, cursor, 0);
        this.dia = dia;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_item_row, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        DateFormat df = new SimpleDateFormat("EEE, dd-MM-yyyy", Locale.getDefault());
        String date = df.format(dia.getDate());

        TextView dateView = (TextView) view.findViewById(R.id.list_view_item_row_date);
        TextView titleView = (TextView) view.findViewById(R.id.list_view_item_row_title);
        TextView aulaView = (TextView) view.findViewById(R.id.list_view_item_row_aula);

        String title = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_SIGLES));
        String aula = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_AULA));

        dateView.setText(date);
        titleView.setText(title);
        aulaView.setText(aula);
    }
}
