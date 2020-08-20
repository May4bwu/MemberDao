package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Member;

public class MemberDaoImpl implements MemberDao {

	private DataSource ds;

	public MemberDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Member> findAll() throws Exception {
		List<Member> memberList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * ,member_types.name AS type_name FROM members JOIN member_types ON members.type_id = member_types.id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				memberList.add(mapToMember(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return memberList;
	}

	@Override
	 public Member findById(Integer id) throws Exception {
	 Member member = null;
	 try (Connection con = ds.getConnection()) {
	 String sql = "SELECT *,member_types.name AS type_name FROM members JOIN member_types ON members.type_id = member_types.id WHERE members.id = ?";
	 PreparedStatement stmt = con.prepareStatement(sql);
	 stmt.setInt(1, id);
	 ResultSet rs = stmt.executeQuery();
	 if (rs.next()) {
	 member = mapToMember(rs);
	 }
	 } catch (Exception e) {
	 throw e;
	 }
		return member;
	}

	@Override
	public void insert(Member member) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO members (name, age, address, type_id, created) VALUES (?, ?, ?, ?, NOW())";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setObject(2, member.getAge(), Types.INTEGER);
			stmt.setString(3, member.getAddress());
			stmt.setObject(4, member.getTypeId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	 public void update(Member member) throws Exception {
	 try (Connection con = ds.getConnection()) {
	 String sql = "UPDATE members SET name=?, age=?, address=?, type_id=? WHERE id=?";
	 PreparedStatement stmt = con.prepareStatement(sql);
	 stmt.setString(1, member.getName());
	 stmt.setObject(2, member.getAge(), Types.INTEGER);
	 stmt.setString(3, member.getAddress());
	 stmt.setObject(4, member.getTypeId(), Types.INTEGER);
	 stmt.setInt(5, member.getId());
	 stmt.executeUpdate();
	 } catch (Exception e) {
	 throw e;
	 }
	}

	@Override
	public void delete(Member member) throws Exception {
		int id = member.getId();
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM members WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	private Member mapToMember(ResultSet rs) throws Exception {
		Member member = new Member();
		member.setId((Integer) rs.getObject("id"));
		member.setName(rs.getString("name"));
		member.setAge((Integer) rs.getObject("age"));
		member.setAddress(rs.getString("address"));
		member.setTypeId((Integer) rs.getObject("type_id"));
		member.setCreated(rs.getTimestamp("created"));
		member.setTypeName(rs.getString("type_name"));
		return member;
	}
}
