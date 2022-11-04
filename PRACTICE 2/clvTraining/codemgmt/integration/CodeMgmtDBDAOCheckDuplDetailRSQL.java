/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtDBDAOCheckDuplDetailRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.10.26
*@LastModifier : 
*@LastVersion : 1.0
* 2022.10.26 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clvtraining.codemgmt.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CodeMgmtDBDAOCheckDuplDetailRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Check duplicated cd id detail
	  * </pre>
	  */
	public CodeMgmtDBDAOCheckDuplDetailRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clvtraining.codemgmt.integration ").append("\n"); 
		query.append("FileName : CodeMgmtDBDAOCheckDuplDetailRSQL").append("\n"); 
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
		query.append("	INTG_CD_ID" ).append("\n"); 
		query.append(",	INTG_CD_VAL_CTNT" ).append("\n"); 
		query.append("FROM COM_INTG_CD_DTL" ).append("\n"); 
		query.append("WHERE INTG_CD_ID IN (" ).append("\n"); 
		query.append("#foreach($intgcd in ${intgList})" ).append("\n"); 
		query.append("	#if($velocityCount < $intgList.size()) '$intgcd' , #else '$intgcd' #end" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append(") AND INTG_CD_VAL_CTNT IN (" ).append("\n"); 
		query.append("#foreach($intgval in ${intgValList})" ).append("\n"); 
		query.append("	#if($velocityCount < $intgValList.size()) '$intgval' , #else '$intgval' #end" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append(")" ).append("\n"); 

	}
}