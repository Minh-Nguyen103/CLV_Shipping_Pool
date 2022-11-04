/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtDBDAOCheckDuplMasterRSQL.java
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

public class CodeMgmtDBDAOCheckDuplMasterRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Check duplicated cd id master
	  * </pre>
	  */
	public CodeMgmtDBDAOCheckDuplMasterRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clvtraining.codemgmt.integration ").append("\n"); 
		query.append("FileName : CodeMgmtDBDAOCheckDuplMasterRSQL").append("\n"); 
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
		query.append("FROM COM_INTG_CD" ).append("\n"); 
		query.append("WHERE INTG_CD_ID IN (" ).append("\n"); 
		query.append("#foreach($intgcd in ${intgList})" ).append("\n"); 
		query.append("	#if($velocityCount < $intgList.size()) '$intgcd' , #else '$intgcd' #end" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append(")" ).append("\n"); 

	}
}