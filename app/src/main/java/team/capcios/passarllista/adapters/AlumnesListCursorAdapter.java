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
import team.capcios.passarllista.model.Alumne;

public class AlumnesListCursorAdapter extends CursorAdapter {

    Cursor cursor;
    int checked;
    int unChecked;
    AlumneCheckList.getAlumneAssistenceToDispaly getAlumneAssistenceToDispaly;

    public AlumnesListCursorAdapter(Context context, Cursor cursor,
                                    AlumneCheckList.getAlumneAssistenceToDispaly getAlumneAssistenceToDispaly) {
        super(context, cursor, 0);
        this.cursor = cursor;
        this.checked = context.getResources().getColor(R.color.alumneChecked);
        this.unChecked = context.getResources().getColor(R.color.alumneUnchecked);
        this.getAlumneAssistenceToDispaly = getAlumneAssistenceToDispaly;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.alumnes_list_item_row, parent, false);
    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.alumnes_list_view_item_row_name);
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_NOM));
        name.setText(title);

        TextView email = (TextView) view.findViewById(R.id.alumnes_list_view_item_row_email);
        String mail = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_MAIL));
        email.setText(mail);

        CustomCursor customCursor = new CustomCursor(cursor);
        Alumne alumne = customCursor.cursorToAlumne();
        boolean eval = getAlumneAssistenceToDispaly.getAlumneAssistence(alumne.getId());
        if(eval){
            view.setBackgroundColor(checked);
        } else {
            view.setBackgroundColor(unChecked);
        }
    }
}
