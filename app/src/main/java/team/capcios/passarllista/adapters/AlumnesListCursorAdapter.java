package team.capcios.passarllista.adapters;

import android.content.Context;
import android.database.Cursor;
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

    private Cursor cursor;
    private int checked;
    private int unChecked;
    private AlumneCheckList.getAlumneAssistenceToDispaly getAlumneAssistenceToDispaly;
    private TextView name;
    private String title;
    private TextView email;
    private String mail;
    private Alumne alumne;
    private CustomCursor customCursor;
    private boolean eval;

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
        name = (TextView) view.findViewById(R.id.alumnes_list_view_item_row_name);
        title = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_NOM));
        name.setText(title);

        email = (TextView) view.findViewById(R.id.alumnes_list_view_item_row_email);
        mail = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_MAIL));
        email.setText(mail);

        customCursor = new CustomCursor(cursor);
        alumne = customCursor.cursorToAlumne();
        eval = getAlumneAssistenceToDispaly.getAlumneAssistence(alumne.getId());
        if(eval){
            view.setBackgroundColor(checked);
        } else {
            view.setBackgroundColor(unChecked);
        }
    }
}
