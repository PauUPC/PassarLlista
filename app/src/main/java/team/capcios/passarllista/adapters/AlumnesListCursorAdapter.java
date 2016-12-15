package team.capcios.passarllista.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import team.capcios.passarllista.MainActivity;
import team.capcios.passarllista.R;
import team.capcios.passarllista.database.DadesDatabaseHelper;

public class AlumnesListCursorAdapter extends CursorAdapter {

    public AlumnesListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.alumnes_list_item_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.alumnes_list_view_item_row_name);
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_NOM));
        name.setText(title);
    }
}
