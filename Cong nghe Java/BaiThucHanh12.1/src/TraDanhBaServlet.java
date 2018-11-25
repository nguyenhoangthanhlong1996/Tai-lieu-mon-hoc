
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TraDanhBaServlet
 */
@WebServlet("/TraDanhBaServlet")
public class TraDanhBaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TraDanhBaServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// tạo các tag HTML ở ₫ầu trang web kết quả
		out.println("<html>");
		out.println("<head><title>SearchAccount</title></head>");
		out.println("<body>");
		out.println("<h2>Kết quả tra ₫iện thoại theo yêu cầu của bạn :</h2>");
		// tìm nội dung các tham số ₫ược client gởi ₫ến
		String tentbao = request.getParameter("txtTenthuebao");
		String sodthoai = request.getParameter("txtSodienthoai");
		String diachi = request.getParameter("txtDiachi");
		// xây dựng lệnh SQL ₫ể tìm kết quả
		boolean fsqlstart = true;
		String newSQL = "SELECT * FROM danhbadienthoai";
		if (tentbao != null && tentbao.length() != 0) {
			newSQL = newSQL + " where tenthuebao like '%" + tentbao + "%'";
			fsqlstart = false;
		}
		if (sodthoai != null && sodthoai.length() != 0) {
			if (fsqlstart)
				newSQL = newSQL + " where sodienthoai like '%" + sodthoai + "%'";
			else
				newSQL = newSQL + " and sodienthoai like '%" + sodthoai + "%'";
			fsqlstart = false;
		}
		if (diachi != null && diachi.length() != 0) {
			if (fsqlstart)
				newSQL = newSQL + " where diachi like '%" + diachi + "%'";
			else
				newSQL = newSQL + " and diachi like '%" + diachi + "%'";
			fsqlstart = false;
		}
		// xây dựng chuỗi ConnectionString truy xuất database dùng DSN
		String conStr = "jdbc:odbc:MyDatabase";
		// ₫ịnh nghĩa các biến cân dùng
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// kết nối database
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection(conStr, "", "");
			stmt = con.createStatement();
			// thực thi lệnh truy vấn SQL và chứa kết quả vào rs
			rs = stmt.executeQuery(newSQL);
			int i = 1;
			if (rs != null && rs.next()) {
				// nếu có ít nhất 1 kết quả thì tạo bảng hiển thị,
				out.println("<table border=1 cellPadding=1 cellSpacing=1>");
				// tạo hàng tiêu ₫ề
				out.println("<tr><th>Số thự tự</th><th>Tên thuê bao</th><th>Số ₫iện thoại</th><th>Địa chỉ</th></tr>\n");
				// tạo hàng dữ liệu ₫ầu tiên
				out.println("<tr><td>" + i++ + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td><td>" + rs.getString("diachi") + "</td></tr>\n");
				while (rs.next()) { // lặp xuất từng hàng kết quả còn lại
					out.println("<tr><td>" + i++ + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
							+ "</td><td>" + rs.getString("diachi") + "</td></tr>\n");
				}
				out.println("</table>");
			} else
				out.println("<p>Không tìm ₫ược account nào thỏa yêu cầu của bạn!<br>");
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		// ₫óng các ₫ối tượng ₫ã dùng lại
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception _ex) {
					System.out.println("Exception: " + _ex);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception _ex) {
					System.out.println("Exception: " + _ex);
				}
			}
		}
	}
}
