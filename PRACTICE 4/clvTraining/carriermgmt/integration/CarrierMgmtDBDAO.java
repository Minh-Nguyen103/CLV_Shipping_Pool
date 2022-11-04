package com.clt.apps.opus.esm.clvtraining.carriermgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clvtraining.carriermgmt.vo.CarrierMgmtVO;
import com.clt.apps.opus.esm.clvtraining.codemgmt.integration.CodeMgmtDBDAOCodeMgmtMasterRSQL;
import com.clt.apps.opus.esm.clvtraining.codemgmt.integration.CodeMgmtDBDAOInsertMasterCSQL;
import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class CarrierMgmtDBDAO extends DBDAOSupport {
	
	 /**
	  * search joo carrier list
	  * 
	  * @param CarrierMgmtVO carrierMgmtVO
	  * @return List<CarrierMgmtVO>
	  * @exception DAOException
	  */
	public List<CarrierMgmtVO> searchCarrier(CarrierMgmtVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CarrierMgmtVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		ArrayList<String> joCrrCdList = new ArrayList<String>();
		
		try {
			if(carrierMgmtVO != null) {
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
				 String[] joCrrCd = carrierMgmtVO.getJoCrrCd().split(",");
				 if(joCrrCd.length != 0) {
					 for (String joCrr : joCrrCd) {
						 joCrrCdList.add(joCrr);
					}
					 param.put("joCrrList", joCrrCdList);
					 velParam.put("joCrrList", joCrrCdList);
				 }
				 
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOCarrierMgmtVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CarrierMgmtVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 /**
	  * check duplicated
	  * 
	  * @param CarrierMgmtVO carrierMgmtVO
	  * @return List<CarrierMgmtVO>
	  * @exception DAOException
	  */
	public List<CarrierMgmtVO> checkDupl(CarrierMgmtVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CarrierMgmtVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			if(carrierMgmtVO != null) {
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOCheckDuplRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CarrierMgmtVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 /**
	  * search carrier list
	  * 
	  * @param CarrierMgmtVO carrierMgmtVO
	  * @return List<CarrierMgmtVO>
	  * @exception DAOException
	  */
	public List<CarrierMgmtVO> searchCarrierList(CarrierMgmtVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CarrierMgmtVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			System.out.println(carrierMgmtVO);
			if(carrierMgmtVO != null) {
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			System.out.println(param);
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOSearchCrrListRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CarrierMgmtVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 /**
	  * search rlane list
	  * 
	  * @param CarrierMgmtVO carrierMgmtVO
	  * @return List<CarrierMgmtVO>
	  * @exception DAOException
	  */
	public List<CarrierMgmtVO> searchRLane(CarrierMgmtVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CarrierMgmtVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			if(carrierMgmtVO != null) {
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOSearchRLaneRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CarrierMgmtVO.class);
			System.out.println(list.size());
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 /**
	  * search vendor list
	  * 
	  * @param CarrierMgmtVO carrierMgmtVO
	  * @return List<CarrierMgmtVO>
	  * @exception DAOException
	  */
	public List<CarrierMgmtVO> searchVendor(CarrierMgmtVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CarrierMgmtVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			if(carrierMgmtVO != null) {
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOSearchVendorRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CarrierMgmtVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 /**
	  * search customer code list
	  * 
	  * @param CarrierMgmtVO carrierMgmtVO
	  * @return List<CarrierMgmtVO>
	  * @exception DAOException
	  */
	public List<CarrierMgmtVO> searchCusCode(CarrierMgmtVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CarrierMgmtVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			if(carrierMgmtVO != null) {
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOSearchCusCodeRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CarrierMgmtVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 /**
	  * search trade list
	  * 
	  * @param CarrierMgmtVO carrierMgmtVO
	  * @return List<CarrierMgmtVO>
	  * @exception DAOException
	  */
	public List<CarrierMgmtVO> searchTrade(CarrierMgmtVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowSet = null;
		List<CarrierMgmtVO> list = null;
				
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();	
		
		try {
			if(carrierMgmtVO != null) {
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOSearchTradeRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowSet, CarrierMgmtVO.class);
			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	 /**
	  * insert new joo carrier
	  * 
	  * @param List<CarrierMgmtVO> carrierMgmtVO
	  * @return int[]
	  * @exception DAOException
	  */
	public int[] addmanageCarrierS(List<CarrierMgmtVO> carrierMgmtVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierMgmtVO.size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOInsertCSQL(), carrierMgmtVO,null);
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
	
	 /**
	  * update joo carrier
	  * 
	  * @param List<CarrierMgmtVO> carrierMgmtVO
	  * @return int[]
	  * @exception DAOException
	  */
	public int[] updateCarrierS(List<CarrierMgmtVO> carrierMgmtVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierMgmtVO.size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOUpdateUSQL(), carrierMgmtVO,null);
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
	
	 /**
	  * delete joo carrier
	  * 
	  * @param List<CarrierMgmtVO> carrierMgmtVO
	  * @return int[]
	  * @exception DAOException
	  */
	public int[] deleteCarrierS(List<CarrierMgmtVO> carrierMgmtVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierMgmtVO.size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAODeleteDSQL(), carrierMgmtVO,null);
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

}
