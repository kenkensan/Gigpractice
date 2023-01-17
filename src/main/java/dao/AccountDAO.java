package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Account;
import dto.Kadaidto;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class AccountDAO {

	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerAccount(Account account) {
		String sql = "INSERT INTO account VALUES(default, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
		
		// ランダムなソルトの取得(今回は32桁で実装)
		String salt = GenerateSalt.getSalt(32);
		
		// 取得したソルトを使って平文PWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(account.getPassword(), salt);
		
		System.out.println("登録時のソルト"+salt);
		System.out.println("登録時のハッシュパスワード"+hashedPw);
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, account.getName());
			pstmt.setString(2, account.getMail());
			pstmt.setString(3, salt);
			pstmt.setString(4, hashedPw);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	public static int registerKaiin(Kadaidto kadaidto) {
		String sql="INSERT INTO kaiin VALUES(default,?,?,?,?,?,?,?)";
		int result=0;
		
		String salt=GenerateSalt.getSalt(32);
		
		String hashedPw = GenerateHashedPw.getSafetyPassword(kadaidto.getPassword(), salt);
		
		try(
				Connection con=getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql);
				){
			pstmt.setString(1, kadaidto.getName());
			pstmt.setInt(2, kadaidto.getAge());
			pstmt.setString(3, kadaidto.getGender());
			pstmt.setInt(4, kadaidto.getTell());
			pstmt.setString(5, kadaidto.getMail());
			pstmt.setString(6, salt);
			pstmt.setString(7,hashedPw);
			
			result =pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}finally {
			System.out.println(result+"件更新しました。");
		}
		return result;
	}
	// メールアドレスを元にソルトを取得
		public static String getSalt(String mail) {
			String sql = "SELECT salt FROM account WHERE mail = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String salt = rs.getString("salt");
						return salt;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// ログイン処理
		public static Account login(String mail, String hashedPw) {
			String sql = "SELECT * FROM account WHERE mail = ? AND password = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);
				pstmt.setString(2, hashedPw);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String salt = rs.getString("salt");
						
						return new Account(id, name, mail, salt, null, null);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		public static String getSaltkaiin(String mail) {
			String sql = "SELECT salt FROM kaiin WHERE mail = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String salt = rs.getString("salt");
						return salt;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public static Kadaidto loginkaiin(String mail, String hashedPw) {
			String sql = "SELECT * FROM kaiin WHERE mail = ? AND password = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);
				pstmt.setString(2, hashedPw);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String name = rs.getString("name");
						int age=rs.getInt("age");
						String gender=rs.getString("gender");
						int tell=rs.getInt("tell");
						String salt = rs.getString("salt");
						
						return new Kadaidto(name,age,gender,tell, mail, salt, null, null);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
}
