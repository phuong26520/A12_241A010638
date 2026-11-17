package vn.edu.vhu.ltdd.a12sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDao {

    private final DbHelper helper;

    public SinhVienDao(Context context) {
        this.helper = new DbHelper(context.getApplicationContext());
    }

    public long themMoi(SinhVien sv) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(DbHelper.COL_MA_SV, sv.getMaSv());
        v.put(DbHelper.COL_HO_TEN, sv.getHoTen());
        v.put(DbHelper.COL_LOP, sv.getLop());
        v.put(DbHelper.COL_DIEM, sv.getDiem());
        long id = db.insert(DbHelper.TABLE, null, v);
        if (id != -1) sv.setId(id);
        return id;
    }

    public List<SinhVien> layTatCa() {
        List<SinhVien> ds = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(DbHelper.TABLE, null, null, null, null, null,
                DbHelper.COL_HO_TEN + " ASC");
        try {
            int iId = c.getColumnIndexOrThrow(DbHelper.COL_ID);
            int iMa = c.getColumnIndexOrThrow(DbHelper.COL_MA_SV);
            int iTen = c.getColumnIndexOrThrow(DbHelper.COL_HO_TEN);
            int iLop = c.getColumnIndexOrThrow(DbHelper.COL_LOP);
            int iDiem = c.getColumnIndexOrThrow(DbHelper.COL_DIEM);
            while (c.moveToNext()) {
                ds.add(new SinhVien(c.getLong(iId), c.getString(iMa),
                        c.getString(iTen), c.getString(iLop), c.getDouble(iDiem)));
            }
        } finally {
            c.close();
        }
        return ds;
    }

    public int dem() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE, null);
        try {
            return c.moveToFirst() ? c.getInt(0) : 0;
        } finally {
            c.close();
        }
    }

    public double diemTrungBinh() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT AVG(" + DbHelper.COL_DIEM + ") FROM " + DbHelper.TABLE, null);
        try {
            return c.moveToFirst() ? c.getDouble(0) : 0;
        } finally {
            c.close();
        }
    }

    public int demDat() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + DbHelper.TABLE
                + " WHERE " + DbHelper.COL_DIEM + " >= 5", null);
        try {
            return c.moveToFirst() ? c.getInt(0) : 0;
        } finally {
            c.close();
        }
    }

    public double diemCaoNhat() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT MAX(" + DbHelper.COL_DIEM + ") FROM " + DbHelper.TABLE, null);
        try {
            return c.moveToFirst() ? c.getDouble(0) : 0;
        } finally {
            c.close();
        }
    }

    public void dong() {
        helper.close();
    }
}
