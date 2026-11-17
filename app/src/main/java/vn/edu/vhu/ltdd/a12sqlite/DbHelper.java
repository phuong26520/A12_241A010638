package vn.edu.vhu.ltdd.a12sqlite;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
import androidx.annotation.Nullable;
 
/**
 * Lớp quản lý tệp cơ sở dữ liệu.
 *
 * SQLiteOpenHelper lo giúp ta ba việc:
 *   - Tạo tệp .db lần đầu ứng dụng cần tới (gọi onCreate).
 *   - Gọi onUpgrade khi DATABASE_VERSION tăng lên.
 *   - Mở sẵn kết nối và giữ lại để dùng nhiều lần.
 *
 * Tệp nằm ở /data/data/<package>/databases/ — chỉ ứng dụng của bạn đọc được.
 */
public class DbHelper extends SQLiteOpenHelper {
 
    private static final String TAG = "A12_241A010638";
 
    public static final String DB_NAME = "quanlysv.db";
    public static final int DB_VERSION = 1;   // TĂNG số này mỗi khi đổi cấu trúc bảng
 
    public static final String TABLE = "sinh_vien";
    public static final String COL_ID = "id";
    public static final String COL_MA_SV = "ma_sv";
    public static final String COL_HO_TEN = "ho_ten";
    public static final String COL_LOP = "lop";
    public static final String COL_DIEM = "diem";
 
    /** Câu lệnh tạo bảng. Viết trên nhiều dòng cho dễ đọc. */
    private static final String SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_MA_SV + " TEXT NOT NULL UNIQUE, " +   // UNIQUE: không cho trùng MSSV
            COL_HO_TEN + " TEXT NOT NULL, " +
            COL_LOP + " TEXT, " +
            COL_DIEM + " REAL DEFAULT 0)";
 
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
 
    /** Chỉ chạy MỘT LẦN, lúc tệp .db chưa tồn tại. */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: tạo bảng " + TABLE);
        db.execSQL(SQL_CREATE);
 
        // Thêm vài dòng mẫu để màn hình không trống trơn lần đầu chạy
        db.execSQL("INSERT INTO " + TABLE + " (" + COL_MA_SV + ", " + COL_HO_TEN
                + ", " + COL_LOP + ", " + COL_DIEM + ") VALUES "
                + "('2201234567', 'Nguyễn Văn A', 'CNTT K22', 8.5), "
                + "('2201234568', 'Trần Thị B', 'CNTT K22', 7.0), "
                + "('2201234569', 'Lê Văn C', 'CNTT K22', 9.2)");
    }
 
    /** Chạy khi DB_VERSION tăng. Cách đơn giản nhất cho bài lab: xóa rồi tạo lại. */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "onUpgrade " + oldVersion + " → " + newVersion + ": xóa và tạo lại bảng");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
