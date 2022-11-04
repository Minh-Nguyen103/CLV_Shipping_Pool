/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtDBDAOCheckDuplErrCdRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.10.19
*@LastModifier : 
*@LastVersion : 1.0
* 2022.10.19 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clvtraining.errmsgmgmt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class ErrMsgMgmtDBDAOCheckDuplErrCdRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Check Duplicated Error Message Code
	  * </pre>
	  */
	public ErrMsgMgmtDBDAOCheckDuplErrCdRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clvtraining.errmsgmgmt.integration").append("\n"); 
		query.append("FileName : ErrMsgMgmtDBDAOCheckDuplErrCdRSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("SELECT " ).append("\n"); 
		query.append("	ERR_MSG_CD" ).append("\n"); 
		query.append("FROM COM_ERR_MSG" ).append("\n"); 
		query.append("WHERE ERR_MSG_CD IN (" ).append("\n"); 
		query.append("#foreach($errcd in ${errMsgCdList})" ).append("\n"); 
		query.append("	 #if($velocityCount < $errMsgCdList.size()) '$errcd', #else '$errcd' #end" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append(")" ).append("\n"); 

	}
}