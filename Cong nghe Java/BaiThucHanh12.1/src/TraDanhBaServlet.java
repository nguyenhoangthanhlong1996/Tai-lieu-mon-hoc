
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TraDanhBaServlet
 */
@WebServlet("/ketqua")
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
		out.println("<h2>Kết quả tra điện thoại theo yêu cầu của bạn :</h2>");
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
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String path  = getServletContext().getRealPath("/WEB-INF/danhba.mdb");
			String conStr = "jdbc:ucanaccess://"+path;
			Connection con = DriverManager.getConnection(conStr);
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(newSQL);
			int i = 1;
			if (rs != null && rs.next()) {
				// nếu có ít nhất 1 kết quả thì tạo bảng hiển thị,
				out.println("<table border=1 cellPadding=1 cellSpacing=1>");
				// tạo hàng tiêu ₫ề
				out.println("<tr><th>Số thự tự</th><th>Tên thuê bao</th><th>Số điện thoại</th><th>Địa chỉ</th></tr>\n");
				// tạo hàng dữ liệu ₫ầu tiên
				out.println("<tr><td>" + i++ + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td><td>" + rs.getString("diachi") + "</td></tr>\n");
				while (rs.next()) { // lặp xuất từng hàng kết quả còn lại
					out.println("<tr><td>" + i++ + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
							+ "</td><td>" + rs.getString("diachi") + "</td></tr>\n");
				}
				out.println("</table>");
			} else
				out.println("<p>Không tìm được account nào thỏa yêu cầu của bạn!<br>");
			out.println("<a href='/tradanhba'>Quay về</a>");
			out.println("</body>");
			out.println("</html>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
