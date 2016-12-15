package team.capcios.passarllista.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import team.capcios.passarllista.MainActivity;
import team.capcios.passarllista.R;
import team.capcios.passarllista.database.CustomCursor;
import team.capcios.passarllista.database.DadesDatabaseHelper;

public class AssignaturaListCursorAdapter extends CursorAdapter {

    private MainActivity.OnItemTouchListener onItemTouchListener;

    public AssignaturaListCursorAdapter(Context context, Cursor cursor,
                                        MainActivity.OnItemTouchListener onItemTouchListener) {
        super(context, cursor, 0);
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_item_row, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        final CustomCursor customCursor = new CustomCursor(cursor);

        DateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault());
        String date = df.format(Calendar.getInstance().getTime());


        TextView dateView = (TextView) view.findViewById(R.id.list_view_item_row_date);
        TextView titleView = (TextView) view.findViewById(R.id.list_view_item_row_title);

        String title = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_SIGLES));

        dateView.setText(date);
        titleView.setText(title);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemTouchListener.onClick(customCursor.CursorToAssignatura());
            }
        });
    }
}
