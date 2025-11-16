package com.example.huynhtruongthaoduyen;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TicketAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Ticket> tickets;
    private DatabaseHelper db;

    public TicketAdapter(Context context, ArrayList<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
        this.db = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tickets.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false);
        }

        Ticket ticket = tickets.get(position);

        TextView tvTen = convertView.findViewById(R.id.tvTenPhim);
        TextView tvGio = convertView.findViewById(R.id.tvGioChieu);
        TextView tvGhe = convertView.findViewById(R.id.tvGhe);
        TextView tvTrangThai = convertView.findViewById(R.id.tvTrangThai);
        Button btnDoiXuat = convertView.findViewById(R.id.btnDoiXuat);
        Button btnHuyVe = convertView.findViewById(R.id.btnHuyVe);
        Button btnDoiTrangThai = convertView.findViewById(R.id.btnDoiTrangThai);
        // Gán dữ liệu
        tvTen.setText(ticket.getTenPhim());
        tvGio.setText("Giờ chiếu: " + ticket.getGioChieu());
        tvGhe.setText("Ghế: " + ticket.getGhe());
        tvTrangThai.setText(ticket.getTrangThai());

        // Sự kiện đổi suất chiếu
        btnDoiXuat.setOnClickListener(v -> {
            String[] suatChieu = {"18:00", "19:00", "20:30", "21:00"};

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Chọn suất chiếu mới");
            builder.setItems(suatChieu, (dialog, which) -> {
                String gioMoi = suatChieu[which];

                // Cập nhật trong DB
                db.updateTicketTime(ticket.getId(), gioMoi);

                // Cập nhật trong danh sách hiển thị
                ticket.setGioChieu(gioMoi);
                notifyDataSetChanged();

                Toast.makeText(context, "Đã đổi suất chiếu thành công: " + gioMoi, Toast.LENGTH_SHORT).show();
            });
            builder.show();
        });

        // Sự kiện hủy vé
        btnHuyVe.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xác nhận hủy vé")
                    .setMessage("Bạn có chắc muốn hủy vé phim " + ticket.getTenPhim() + "?")
                    .setPositiveButton("Hủy vé", (dialog, which) -> {
                        // Xóa vé trong DB
                        SQLiteUtils.deleteTicket(db, ticket.getId());
                        tickets.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Đã hủy vé", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });

        btnDoiTrangThai.setOnClickListener(v -> {
            String[] trangThaiOptions = {"Đang giữ chỗ", "Đã thanh toán", "Đã xem"};

            new AlertDialog.Builder(context)
                    .setTitle("Chọn trạng thái mới")
                    .setItems(trangThaiOptions, (dialog, which) -> {
                        String newStatus = trangThaiOptions[which];

                        // Cập nhật DB
                        db.updateTicketStatus(ticket.getId(), newStatus);

                        // Cập nhật danh sách hiển thị
                        ticket.setTrangThai(newStatus);
                        notifyDataSetChanged();

                        Toast.makeText(context, "Đã đổi trạng thái vé: " + newStatus, Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });
        return convertView;
    }
}
