package com.clt.apps.opus.esm.clvtraining.codemgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clvtraining.codemgmt.integration.CodeMgmtDBDAO;
import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.vo.ErrMsgVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class CodeMgmtBCImpl extends BasicCommandSupport implements CodeMgmtBC {
	
	private transient CodeMgmtDBDAO  dbDao = null;
	
	public CodeMgmtBCImpl() {
		dbDao = new CodeMgmtDBDAO();
	}

	@Override
	public List<CodeMgmtMasterVO> searchCodeMgmtMaster(CodeMgmtMasterVO codeMgmtMasterVO) throws EventException {
		try {
			 return dbDao.searchCodeMgmtMaster(codeMgmtMasterVO);		
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	@Override
	public List<CodeMgmtDetailVO> searchCodeMgmtDetail(CodeMgmtDetailVO codeMgmDetailVO) throws EventException{
		try {
			 return dbDao.searchCodeMgmtDetail(codeMgmDetailVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	@Override
	public void manageCodeMgmtDetail(CodeMgmtDetailVO[] codeMgmtDetailVO,
			SignOnUserAccount account) throws EventException {
		try {
			List<CodeMgmtDetailVO> insertVoList = new ArrayList<CodeMgmtDetailVO>();
			List<CodeMgmtDetailVO> updateVoList = new ArrayList<CodeMgmtDetailVO>();
			List<CodeMgmtDetailVO> deleteVoList = new ArrayList<CodeMgmtDetailVO>();
			List<CodeMgmtDetailVO> listDuplicated = new ArrayList<CodeMgmtDetailVO>();
			List<String> listCdID = new ArrayList<String>();
			for ( int i=0; i < codeMgmtDetailVO.length; i++ ) {
				if ( codeMgmtDetailVO[i].getIbflag().equals("I")){ 
					codeMgmtDetailVO[i].setCreUsrId(account.getUsr_id());
					codeMgmtDetailVO[i].setUpdUsrId(account.getUsr_id());
					listCdID.add(codeMgmtDetailVO[i].getIntgCdId());
					insertVoList.add(codeMgmtDetailVO[i]);
				} else if ( codeMgmtDetailVO[i].getIbflag().equals("U")){
					codeMgmtDetailVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(codeMgmtDetailVO[i]);
				} else if ( codeMgmtDetailVO[i].getIbflag().equals("D")){
					deleteVoList.add(codeMgmtDetailVO[i]);
				}
			}

			
			if ( insertVoList.size() > 0 ) {
				List<CodeMgmtMasterVO> cdIdList = dbDao.checkHaveCdID(insertVoList);
				System.out.println(cdIdList);
				System.out.println(cdIdList.size());
				
				if(cdIdList.size() == 0) {
					StringBuilder codeString = new StringBuilder("");
					for (String intgCdId : listCdID) {
						codeString.append(intgCdId + ",");
					}
					String codeStringDupl = codeString.substring(0, codeString.length() -1);
					throw new EventException(new ErrorHandler("ERR00005", new String[]{codeStringDupl}).getMessage());		
					
				}else {
					listDuplicated = dbDao.checkDuplicatedDetail(insertVoList);
					
					if(listDuplicated.size() > 0) {
						StringBuilder codeString = new StringBuilder("");
						for (CodeMgmtDetailVO codeDetailVO : listDuplicated) {
							codeString.append(codeDetailVO.getIntgCdId() + "," + codeDetailVO.getIntgCdValCtnt() + " and ");
						}
						String codeStringDupl = codeString.substring(0, codeString.length() -5);
						throw new EventException(new ErrorHandler("ERR00001", new String[]{codeStringDupl}).getMessage());				
					}else {
						dbDao.addmanageDetailS(insertVoList);
					}
				}

			}
			
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageDetailS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageDetailS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
		


	@Override
	public void manageCodeMgmtMaster(CodeMgmtMasterVO[] codeMgmtMasterVO,
			SignOnUserAccount account) throws EventException {
		try {
			List<CodeMgmtMasterVO> insertVoList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtMasterVO> updateVoList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtMasterVO> deleteVoList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtMasterVO> listDuplicated = new ArrayList<CodeMgmtMasterVO>();
			for ( int i=0; i < codeMgmtMasterVO.length; i++ ) {
				if ( codeMgmtMasterVO[i].getIbflag().equals("I")){ 
					codeMgmtMasterVO[i].setCreUsrId(account.getUsr_id());
					codeMgmtMasterVO[i].setUpdUsrId(account.getUsr_id());;
					insertVoList.add(codeMgmtMasterVO[i]);
				} else if ( codeMgmtMasterVO[i].getIbflag().equals("U")){
					codeMgmtMasterVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(codeMgmtMasterVO[i]);
				} else if ( codeMgmtMasterVO[i].getIbflag().equals("D")){
					deleteVoList.add(codeMgmtMasterVO[i]);
				}
			}

			
			if ( insertVoList.size() > 0 ) {
				
				listDuplicated = dbDao.checkDuplicatedMaster(insertVoList);
				
				if(listDuplicated.size() > 0) {
					StringBuilder codeString = new StringBuilder("");
					for (CodeMgmtMasterVO codeMasterVO : listDuplicated) {
						codeString.append(codeMasterVO.getIntgCdId() + ",");
					}
					String codeStringDupl = codeString.substring(0, codeString.length() -1);
					throw new EventException(new ErrorHandler("ERR00001", new String[]{codeStringDupl}).getMessage());				
				}else {
					dbDao.addmanageMasterS(insertVoList);
				}
			}
			
//			if(insertVoList.size() > 0) {
//				dbDao.addmanageMasterS(insertVoList);
//			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageMasterS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageMasterS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
		
	}




