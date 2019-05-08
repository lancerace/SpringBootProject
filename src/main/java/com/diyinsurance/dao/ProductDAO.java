package com.diyinsurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.model.Insurer;
import com.diyinsurance.model.Objective;
import com.diyinsurance.model.Product;
import com.diyinsurance.model.Promotion;
import com.diyinsurance.model.Purpose;
import com.diyinsurance.model.TypeObject;

//respository make this class a candidate for component scanning
@Repository
public class ProductDAO extends dataSource {

	

	public Product getOneProduct(String mProductID) {
		System.out.println("invoked getOneProduct");
		String SQL = "call getOneProduct(?)";
		Product p = jbT.queryForObject(SQL, new Object[] { mProductID }, new ProductMapper());
		return p;
	}

	public String updateOneProduct(String INprod_id, String INprod_name, String INinsurer_id, int INpurpose_id,
			int INobjective_id, int INtype_id, int INpromo_id, String INwhybuy, String INpayout, String INunderwriting,
			String INrebate, String INcash_value, String INcash_payout_frequency, String INcoverage_duratio,
			String INpremium_duration, String INfeatures, String INprod_desc, String INstatus, int authorised) {

		String SQL = "call updateOneProduct(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		return jbT.update(SQL, INprod_id, INprod_name, INinsurer_id, INpurpose_id, INobjective_id, INtype_id,
				INpromo_id, INwhybuy, INpayout, INunderwriting, INrebate, INcash_value, INcash_payout_frequency,
				INcoverage_duratio, INpremium_duration, INfeatures, INprod_desc, INstatus, authorised) +"";

		
	}

	public String insertOneProduct(String inProductID, String inProductName, String IninsurerID, int inPurposeID,
			int inObjectiveID, int inTypeID, int inPromoID, String inWhyBuy, String inPayOut, String inUnderwriting,
			String inRebate, String inCashValue, String inCashPayoutFrequency, String inCoverageDuration,
			String inPremiumDuration, String inFeatures, String inProductDescription, String inStatus,
			int inAuthorised) {
		System.out.println("inserted1product invoked");

		String sqlcmd = " call insertOneProduct(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		String mProductID = jbT.queryForObject(sqlcmd,
				new Object[] { inProductID, inProductName, IninsurerID, inPurposeID, inObjectiveID, inTypeID, inPromoID,
						inWhyBuy, inPayOut, inUnderwriting, inRebate, inCashValue, inCashPayoutFrequency,
						inCoverageDuration, inPremiumDuration, inFeatures, inProductDescription, inStatus,
						inAuthorised },
				String.class);
		return mProductID;
	}

	// annoymous inner class
	public class ProductMapper implements RowMapper<Product> {

		// jdbc RowMapper functionality/features = map single row of database
		// table
		// field to java object

		// do not have to literate using while loop for resultset?
		// its done on rowMapper functionality alr?? need to evaluate

		public Product mapRow(ResultSet rs, int rowNo) throws SQLException {
			Product mProduct = new Product();

			Insurer mInsurer = new Insurer();
			Purpose mPurpose = new Purpose();
			Objective mObjective = new Objective();
			TypeObject mType = new TypeObject();
			Promotion mPromotion = new Promotion();

			mProduct.setmProductID(rs.getString("prod_id"));

			// mapping multiple table columns to product object
			mInsurer.setmInsurerID(rs.getString("insurer_id"));
			mInsurer.setmInsurerName(rs.getString("insurer_name"));
			mProduct.setmInsurer(mInsurer);
			// mProduct.setmInsurerName(rs.getString("insurer_name"));

			mPurpose.setmPurposeID(rs.getInt("purpose_id"));
			mPurpose.setmPurposeName(rs.getString("purpose_name"));
			mProduct.setmPurpose(mPurpose);
			// mProduct.setmPurposeName(rs.getString("purpose_name"));
			//
			mObjective.setmObjectiveID(rs.getInt("objective_id"));
			mObjective.setmObjectiveName(rs.getString("objective_name"));
			mProduct.setmObjective(mObjective);
			// mProduct.setmObjectiveName(rs.getString("objective_name"));

			mType.setmTypeID(rs.getInt("type_id"));
			mType.setmTypeName(rs.getString("type_name"));
			mProduct.setmType(mType);
			// mProduct.setmTypeName(rs.getString("type_name"));

			mPromotion.setmTitle(rs.getString("promo_title"));
			mPromotion.setmPromoID(rs.getInt("promo_id"));
			mProduct.setmPromotion(mPromotion);
			// mProduct.setmPromotionName(rs.getString("promo_title"));

			// mProduct.setmPromotions(mPromotions);

			mProduct.setmProductName(rs.getString("prod_name"));
			mProduct.setmProductDescription(rs.getString("prod_desc"));
			mProduct.setmWhyBuy(rs.getString("whybuy"));
			mProduct.setmPayOut(rs.getString("payout"));
			mProduct.setmUnderwriting(rs.getString("underwriting"));
			mProduct.setmRebate(rs.getString("rebate"));
			mProduct.setmCashValue(rs.getString("cash_value"));
			mProduct.setmCashPayOutFrequency(rs.getString("cash_payout_frequency"));
			mProduct.setmCoverageDuration(rs.getString("coverage_duration"));
			mProduct.setmPremiumDuration(rs.getString("premium_duration"));
			mProduct.setmFeatures(rs.getString("features"));
			mProduct.setmStatus(rs.getString("status"));
			mProduct.setmAuthorized(rs.getInt("authorised"));
			return mProduct;
		}
	}

}
