package com.diyinsurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.model.Premium;



@Repository
public class PremiumDAO extends dataSource {

	

	public List<Premium> getAllPremiumsByProductID(String mProductID) {
		System.out.println("mProductID issss:" + mProductID);

		String SQL = "call getAllPremiumsByProductID(?)";
		return jbT.query(SQL, new String[] { mProductID }, new PremiumMapper());

	}

	// (prod_id,gender,min_age,coverage_name,duration_name,premium_term,
	// savings_dur,retirement_payout_amt,retirement_payout_dur,premium_amt,ranking)
	public String insertOnePremium(String mProdID, String mGender, int mMinAge, String mCoverageName, String mDurationName,
			String mPremiumTerm, String mSavingDuration, String mRetirementPayoutAmount,
			String mRetirementPayoutDuration, int mPremiumAmt, int mRanking) {
		System.out.println("InsertOnePremium");
		String SQL = " call insertOnePremium(?,?,?,?,?,?,?,?,?,?,?) ";

		/* return 1 if successful insert to db
		 * Server response produce type ="text/plain", 
		 * hence return type has to be in string format which was concatenated with + ""
		*/
		return jbT.update(SQL, mProdID, mGender, mMinAge, mCoverageName, mDurationName, mPremiumTerm, mSavingDuration,
				mRetirementPayoutAmount, mRetirementPayoutDuration, mPremiumAmt, mRanking) + "";

	}

	public Premium getOnePremium(int mPremID) {
		String SQL = "call getOnePremium(?)";
		return jbT.queryForObject(SQL, new Object[] { mPremID }, new PremiumMapper());
	}

	public int updateOnePremium(int mPremID, String mGender, int mMinAge, String mCoverageName, String mDurationName,
			String mPremiumTerm, String mSavingDuration, String mRetirementPayoutAmount,
			String mRetirementPayoutDuration, int mPremiumAmt, int mRanking) {
		String SQL = " call updateOnePremium(?,?,?,?,?,?,?,?,?,?,?) ";

		// int 1 if row updated
		return jbT.update(SQL, mPremID, mGender, mMinAge, mCoverageName, mDurationName, mSavingDuration, mPremiumTerm,
				mPremiumAmt, mRetirementPayoutAmount, mRetirementPayoutDuration, mRanking);

	}

	public int deleteOnePremium(int mPremID) {
		String SQL = " call deleteOnePremium(?) ";

		return jbT.update(SQL, mPremID);
	}

	// annoymous inner class
	public class PremiumMapper implements RowMapper<Premium> {

		public Premium mapRow(ResultSet rs, int rowNo) throws SQLException {
			// TODO Auto-generated method stub

			Premium mPremium = new Premium();
			mPremium
					// .setmProductID(rs.getString("prod_id"))
					.setmPremID(rs.getInt("prem_id")).setmGender(rs.getString("gender"))
					.setmMinAge(rs.getInt("min_age")).setmCoverageName(rs.getString("coverage_name"))
					.setmDurationName(rs.getString("duration_name")).setmPremiumTerm(rs.getString("premium_term"))
					.setmSavingduration(rs.getString("savings_dur"))
					.setmRetirementPayOutAmount(rs.getString("retirement_payout_amt"))
					.setmRetirementPayoutDuration(rs.getString("retirement_payout_dur"))
					.setmPremiumAmount(rs.getInt("premium_amt")).setmRanking(rs.getInt("ranking"));

			return mPremium;
		}
	}
}
