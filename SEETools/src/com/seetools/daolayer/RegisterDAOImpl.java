package com.seetools.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.seetools.dto.UserDTO;

public class RegisterDAOImpl {

	private DataSource  dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public UserDTO registerUser(final UserDTO userDto){
		final String REGISTER_EMAIL_INSERT_STMT = 
			"INSERT INTO EMAIL(EmailAddress,CreatedByUserID,CreatedDate,ModifiedByUserID,ModifiedDate) VALUES (?,?,?,?,?)";
		final String REGISTER_USER_INSERT_STMT = 
			"INSERT INTO USER(EmailID,FirstName,LastName,MobileNumber,Password,MembershipID,CreatedByUserID,CreatedDate,ModifiedByUserID,ModifiedDate) "
			+ "values (?,?,?,?,?,?,?,?,?,?)";
		try{
			this.jdbcTemplate =  new JdbcTemplate(dataSource);
			//Object[] emailParams = new Object[]{userDto.getEmailDto().getEmailAddress(),userDto.getEmailDto().getCreatedByUserId(),userDto.getEmailDto().getCreatedDate(),
			//                                userDto.getEmailDto().getModifiedByUserId(),userDto.getEmailDto().getModifiedDate()};
			//this.jdbcTemplate.update(REGISTER_EMAIL_INSERT_STMT, emailParams);
			KeyHolder emailKeyHolder = new GeneratedKeyHolder();
			KeyHolder userKeyHolder = new GeneratedKeyHolder();
			
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				  public PreparedStatement createPreparedStatement(Connection connection)
				      throws SQLException {
				    PreparedStatement ps = connection.prepareStatement(REGISTER_EMAIL_INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				    ps.setString(1,userDto.getEmailDto().getEmailAddress());
				    ps.setString(2,userDto.getEmailDto().getCreatedByUserId());
				    ps.setTimestamp(3,userDto.getEmailDto().getCreatedDate());
				    ps.setString(4,userDto.getEmailDto().getModifiedByUserId());
				    ps.setTimestamp(5,userDto.getEmailDto().getModifiedDate());
				    return ps;
				  }
				}, emailKeyHolder);
			
			final Long generatedEmailId = new Long(emailKeyHolder.getKey().longValue());
			
			/*Object[] userParams = new Object[]{generatedEmailId, userDto.getFirstName(), userDto.getLastName(),userDto.getMobileNumber(),
					userDto.getPassword(),null,userDto.getCreatedByUserId(),userDto.getCreatedDate(),userDto.getModifiedByUserId(),userDto.getModifiedDate()};*/
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				  public PreparedStatement createPreparedStatement(Connection connection)
				      throws SQLException {
				    PreparedStatement ps = connection.prepareStatement(REGISTER_USER_INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				    ps.setLong(1,generatedEmailId);
				    ps.setString(2,userDto.getFirstName());
				    ps.setString(3, userDto.getLastName());
				    ps.setString(4,userDto.getMobileNumber());
				    ps.setString(5,userDto.getPassword());
				    ps.setString(6,null);
				    ps.setString(7,userDto.getCreatedByUserId());
				    ps.setTimestamp(8,userDto.getCreatedDate());
				    ps.setString(9,userDto.getModifiedByUserId());
				    ps.setTimestamp(10,userDto.getModifiedDate());
				    return ps;
				  }
				}, userKeyHolder);
			
			final Long generatedUserId = new Long(userKeyHolder.getKey().longValue());
			
			userDto.setUserId(generatedUserId.toString());
			
			System.out.println("yaaaaaaa hooooooooo");
			
			
			}
			
			catch(DataAccessException e){
				e.printStackTrace();
			}
			
			catch(Exception e){
				e.printStackTrace();
			}
		return userDto;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
