package team.capcios.passarllista.async;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import java.util.ArrayList;

import team.capcios.passarllista.database.DadesDatabaseHelper;



//        private AsyncDbGetCursor.AsyncDbGetCursorResponse asyncDbGetCursorResponse = null;
//        private DadesDatabaseHelper dadesDatabaseHelper;
//
//        public interface AsyncDbGetCursorResponse {
//            void onFinishAsyncDbGetCursor(Cursor cursor);
//        }
//
//        public AsyncDbGetCursor(Activity listeningActivity, DadesDatabaseHelper dadesDatabaseHelper) {
//            asyncDbGetCursorResponse = (AsyncDbGetCursor.AsyncDbGetCursorResponse) listeningActivity;
//            this.dadesDatabaseHelper = dadesDatabaseHelper;
//        }
//
//        @Override
//        protected Cursor doInBackground(String... key) {
//            switch (key[0]) {
//                case "ALL":
//                    return bookData.getAllBooksQuery();
//                case "TITLE":
//                    return bookData.orderByTitleQuery();
//                default:
//                    return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Book> books) {
//            asyncDbQueryResponse.onFinishAsyncDbQuery(books);
//        }
//    }
//}
