package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {

	public static MemberDao createMemberDao() {
		return new MemberDaoImpl(getDataSource());
	}

	public static AdminDao createAdminDao() {
		return new AdminDaoImpl(getDataSource());
	}


	private static DataSource getDataSource() {
		InitialContext ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
		} catch (NamingException e) {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException el) {
				}
			}
			throw new RuntimeException(e);
		}
		return ds;
	}
}
