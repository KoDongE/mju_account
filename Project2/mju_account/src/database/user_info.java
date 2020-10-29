package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

public class user_info 
{
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static String name = null;
	private static int id;
	public user_info()
	{
		con = DBConnection.getInstance();
	}
	public void register(String name, String id, String pass, String account) throws SQLException
	{
		String query = "INSERT INTO members(name, loginid, password, account) VALUES(?, ?, ?, ?)";
		ps = con.prepareStatement(query);
		ps.setString(1, name);//�̸�
		ps.setString(2, id);//id
		ps.setString(3, pass);//�н�����
		ps.setString(4, account);//�ܰ�
		ps.execute();//��������
		System.out.println("ȸ�����ԵǾ���");
	}
	public boolean login(String id, String pass) throws SQLException
	{
		String query = "SELECT * FROM members WHERE loginid = ? AND password = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, id);//���̵�
		ps.setString(2, pass);//�н�����
		if(ps.execute())//��������
			rs = ps.getResultSet();
		while(rs.next())
		{
			String _id = rs.getString("loginid");
			String _pass = rs.getString("password");
			if(_id.equals(id) && _pass.equals(pass))//���̵�� �н����尡 �����ÿ�
			{
				this.id = rs.getInt("id");//������� �������̵�����
				this.name = rs.getString("name");//������� �̸� ����
				return true;//true�� �����ϰ� �Լ�����
			}
		}
		return false;//while���� �ٵ��Ҵµ� ��ã������ false��.
	}
	public int getId()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}
}
