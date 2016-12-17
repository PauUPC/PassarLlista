package team.capcios.passarllista.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import team.capcios.passarllista.R;
import team.capcios.passarllista.activitys.AlumneCheckList;
import team.capcios.passarllista.database.CustomCursor;
import team.capcios.passarllista.database.DadesDatabaseHelper;

public class AlumnesListCursorAdapter extends CursorAdapter {

    private AlumneCheckList.OnItemTouchListener onItemTouchListener;
    private int checked;
    private int unChecked;

    public AlumnesListCursorAdapter(Context context, Cursor cursor,
                                    AlumneCheckList.OnItemTouchListener onItemTouchListener) {
        super(context, cursor, 0);
        this.onItemTouchListener = onItemTouchListener;
        checked = context.getResources().getColor(R.color.alumneChecked);
        unChecked = context.getResources().getColor(R.color.alumneUnchecked);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.alumnes_list_item_row, parent, false);
    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {
        final CustomCursor customCursor = new CustomCursor(cursor);

        TextView name = (TextView) view.findViewById(R.id.alumnes_list_view_item_row_name);
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_NOM));
        name.setText(title);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable drawable = (ColorDrawable)view.getBackground();
                if(drawable.getColor() == checked)
                    view.setBackgroundColor(unChecked);
                else
                    view.setBackgroundColor(checked);
                onItemTouchListener.onClick(customCursor.cursorToAlumne());
            }
        });
    }
}
