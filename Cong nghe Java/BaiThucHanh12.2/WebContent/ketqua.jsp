<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@page import="java.sql.*"%>
<%!public String SearchAccount(String tentbao, String sodthoai, String diachi) {
		StringBuffer table = new StringBuffer();
		Connection con;
		//xây dựng lệnh SQL ₫ể tìm kết quả
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
		//xây dựng chuỗi ConnectionString truy xuất database dùng DSN
		String path = getServletContext().getRealPath("/WEB-INF/danhba.mdb");
		String conStr = "jdbc:ucanaccess://" + path;
		//₫ịnh nghĩa các biến cân dùng
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//kết nối database
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = DriverManager.getConnection(conStr);
			stmt = con.createStatement();
			//thực thi lệnh truy vấn SQL và chứa kết quả vào rs
			rs = stmt.executeQuery(newSQL);
			int i = 1;
			if (rs != null && rs.next()) {
				//nếu có ít nhất 1 kết quả thì tạo bảng hiển thị,
				table.append("<table border=1 cellPadding=1 cellSpacing=1>");
				//tạo hàng tiêu ₫ề
				table.append(
						"<tr><th>Số thự tự</th><th>Tên thuê bao</th><th>Số ₫iện thoại</th><th>Địa chỉ</th></tr>\n");
				//tạo hàng dữ liệu ₫ầu tiên
				table.append("<tr><td>" + i++ + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td><td>" + rs.getString("diachi") + "</td></tr>\n");
				while (rs.next()) { //lặp xuất từng hàng kết quả còn lại
					table.append("<tr><td>" + i++ + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
							+ "</td><td>" + rs.getString("diachi") + "</td></tr>\n");
				}
				table.append("</table>");
			} else
				table.append("<p>Không tìm ₫ược account nào thỏa yêu cầu của bạn!<br>");
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		//₫óng các ₫ối tượng ₫ã dùng lại
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
		//trả kết quả về lệnh gọi
		return table.toString();
	}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>Insert title here</title>
</head>
<body>
	<h3>Kết quả tra danh bạ theo yêu cầu của bạn :</h3>
	<%=SearchAccount(request.getParameter("txtTenthuebao"),request.getParameter("txtSodienthoai"),request.getParameter("txtDiachi"))%>
	<p>
		<a href="index.html">Quay về trang chủ</a>
	</p>
</body>
</html>