Lab A12 - SQLite (1): tạo CSDL, thêm và truy vấn dữ liệu - Quản lý sinh viên

Thông tin sinh viên: Nguyễn Hoài Phương - MSSV 241A010638 - Lớp LTTTBDD - Môn INT4211.

Mô tả ngắn gọn

App Quản lý sinh viên dùng SQLite để lưu dữ liệu trên thiết bị. Người dùng nhập MSSV, họ tên, lớp và điểm rồi thêm vào CSDL. Danh sách được đọc lại từ SQLite bằng DAO và hiển thị bằng RecyclerView. App có dòng thống kê tổng số sinh viên, điểm trung bình, số sinh viên đạt và điểm cao nhất.

Cấu trúc chính

app/src/main/java/vn/edu/vhu/ltdd/a12sqlite/SinhVien.java: model một sinh viên.
app/src/main/java/vn/edu/vhu/ltdd/a12sqlite/DbHelper.java: tạo và nâng cấp CSDL SQLite.
app/src/main/java/vn/edu/vhu/ltdd/a12sqlite/SinhVienDao.java: lớp DAO chứa các lệnh truy vấn và thêm dữ liệu.
app/src/main/java/vn/edu/vhu/ltdd/a12sqlite/MainActivity.java: nhận dữ liệu từ giao diện và gọi DAO, không viết SQL trực tiếp.
app/src/main/java/vn/edu/vhu/ltdd/a12sqlite/SinhVienAdapter.java: hiển thị sinh viên bằng RecyclerView.
app/src/main/res/layout/: giao diện nhập liệu và một dòng sinh viên.

Các chức năng đã làm

- Tạo CSDL quanlysv.db và bảng sinh_vien bằng SQLiteOpenHelper.
- Bảng có id tự tăng, MSSV UNIQUE và NOT NULL, điểm có DEFAULT 0.
- Thêm sinh viên bằng ContentValues và kiểm tra id trả về -1.
- Đọc dữ liệu bằng query và Cursor, đóng Cursor trong finally.
- Tách toàn bộ SQL vào SinhVienDao.
- Hiển thị danh sách bằng RecyclerView.
- Thống kê tổng số sinh viên và điểm trung bình.
- Dữ liệu vẫn còn sau khi tắt hẳn app rồi mở lại.

Bài nâng cao đã làm

NC1 - Lưu tên lớp mặc định bằng SharedPreferences

Bài yêu cầu lưu lại tên lớp người dùng nhập để lần sau tự điền. Mình sử dụng SharedPreferences với tên cai_dat. Khi mở app, chương trình đọc giá trị lop_mac_dinh và đặt vào ô Lớp nếu giá trị này tồn tại. Sau khi thêm sinh viên thành công, tên lớp hiện tại được lưu lại bằng edit().putString(...).apply(). SharedPreferences chỉ dùng cho cài đặt nhỏ, còn danh sách sinh viên vẫn lưu trong SQLite.

NC2 - Thống kê số sinh viên đạt và điểm cao nhất

Bài yêu cầu thêm số sinh viên đạt (điểm từ 5 trở lên) và điểm cao nhất. Trong SinhVienDao, hàm demDat() dùng rawQuery với điều kiện diem >= 5, còn diemCaoNhat() dùng hàm MAX(diem). Hai hàm đều dùng Cursor và đóng Cursor trong finally. MainActivity gọi hai hàm trong napDanhSach() rồi hiển thị kết quả cùng tổng số và điểm trung bình. Cách này để SQLite tự tính bằng SQL, không phải lấy toàn bộ danh sách ra Java để tự tính.

Cách chạy và kiểm thử

Mở project A12_241A010638 trong Android Studio rồi chạy app. Kiểm tra thêm sinh viên, nhập trùng MSSV, nhập điểm sai, tắt hẳn app rồi mở lại. Mở View → Tool Windows → App Inspection → Database Inspector để xem bảng sinh_vien và các dòng dữ liệu.
