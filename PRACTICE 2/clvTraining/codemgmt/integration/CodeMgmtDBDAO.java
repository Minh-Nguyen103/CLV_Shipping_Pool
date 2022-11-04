package com.clt.apps.opus.esm.clvtraining.codemgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOCheckDuplErrCdRSQL;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOErrMsgVOCSQL;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOErrMsgVODSQL;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOErrMsgVOUSQL;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.vo.ErrMsgVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class CodeMgmtDBDAO extends DBDAOSupport {
	
	public List<CodeMgmtMasterVO> searchCodeMgmtMaster(CodeMgmtMasterVO codeMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CodeMgmtMasterVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			if(codeMgmtVO != null) {
				Map<String, String> mapVO = codeMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CodeMgmtDBDAOCodeMgmtMasterRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CodeMgmtMasterVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	
	public List<CodeMgmtDetailVO> searchCodeMgmtDetail(CodeMgmtDetailVO codeMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CodeMgmtDetailVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			if(codeMgmtVO != null) {
				Map<String, String> mapVO = codeMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CodeMgmtDBDAOCodeMgmtDetailRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CodeMgmtDetailVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 public List<CodeMgmtMasterVO> checkDuplicatedMaster(List<CodeMgmtMasterVO> insertVoList) throws DAOException {
			DBRowSet dbRowset = null;
			List<CodeMgmtMasterVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			ArrayList<String> codeMgmtCdIdList = new ArrayList<String>();

			try{
				if(insertVoList != null){
					for (CodeMgmtMasterVO codeMgmtMaster : insertVoList) {
						codeMgmtCdIdList.add(codeMgmtMaster.getIntgCdId());
					}
				
					param.put("intgList", codeMgmtCdIdList);
					velParam.put("intgList", codeMgmtCdIdList);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeMgmtDBDAOCheckDuplMasterRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtMasterVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
		}
	 
	 public List<CodeMgmtMasterVO> checkHaveCdID(List<CodeMgmtDetailVO> insertVoList) throws DAOException {
			DBRowSet dbRowset = null;
			List<CodeMgmtMasterVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			ArrayList<String> codeMgmtCdIdList = new ArrayList<String>();

			try{
				if(insertVoList != null){
					for (CodeMgmtDetailVO codeMgmtDetail : insertVoList) {
						codeMgmtCdIdList.add(codeMgmtDetail.getIntgCdId());
					}
				
					param.put("intgList", codeMgmtCdIdList);
					velParam.put("intgList", codeMgmtCdIdList);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeMgmtDBDAOCheckDuplMasterRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtMasterVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
		}
	 
	 public List<CodeMgmtDetailVO> checkDuplicatedDetail(List<CodeMgmtDetailVO> insertVoList) throws DAOException {
			DBRowSet dbRowset = null;
			List<CodeMgmtDetailVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			ArrayList<String> paramCdID = new ArrayList<String>();
			ArrayList<String> paramValue = new ArrayList<String>();

			try{
				if(insertVoList != null){
					for (CodeMgmtDetailVO codeMgmtDetail : insertVoList) {
						paramCdID.add(codeMgmtDetail.getIntgCdId());
						paramValue.add(codeMgmtDetail.getIntgCdValCtnt());
					}
				
					param.put("intgList", paramCdID);
					param.put("intgValList", paramValue);
					
					velParam.put("intgList", paramCdID);
					velParam.put("intgValList", paramValue);
					
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeMgmtDBDAOCheckDuplDetailRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtDetailVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
		}
	
	public int[] addmanageMasterS(List<CodeMgmtMasterVO> codeMgmtMasterVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtMasterVO.size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOInsertMasterCSQL(), codeMgmtMasterVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	
	public int[] addmanageDetailS(List<CodeMgmtDetailVO> codeMgmtDetailVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtDetailVO.size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOInsertDetailCSQL(), codeMgmtDetailVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	
	public int[] modifymanageMasterS(List<CodeMgmtMasterVO> codeMgmtMasterVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtMasterVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOUpdateMasterUSQL(), codeMgmtMasterVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	public int[] modifymanageDetailS(List<CodeMgmtDetailVO> codeMgmtDetailVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtDetailVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOUpdateDetailUSQL(), codeMgmtDetailVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	public int[] removemanageMasterS(List<CodeMgmtMasterVO> codeMgmtMasterVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtMasterVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAODeleteMasterDSQL(), codeMgmtMasterVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	
	public int[] removemanageDetailS(List<CodeMgmtDetailVO> codeMgmtDetailVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtDetailVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAODeleteDetailDSQL(), codeMgmtDetailVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	

}
