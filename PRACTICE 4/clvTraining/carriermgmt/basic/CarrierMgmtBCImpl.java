package com.clt.apps.opus.esm.clvtraining.carriermgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clvtraining.carriermgmt.integration.CarrierMgmtDBDAO;
import com.clt.apps.opus.esm.clvtraining.carriermgmt.vo.CarrierMgmtVO;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.vo.ErrMsgVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Training Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Training<br>
 *
 * @author Nguyen
 * @since J2EE 1.6
 */
public class CarrierMgmtBCImpl extends BasicCommandSupport implements CarrierMgmtBC {
	// Database Access Object
	public CarrierMgmtDBDAO dbDao = null;
	
	/**
	* Create JooCarrierMgmtBCImpl object<br>
	* Create JooCarrierMgmtDBDAO.<br>
	*/
	public CarrierMgmtBCImpl(){
		dbDao = new CarrierMgmtDBDAO();
	}

	/**
	 * Search Joo Carrier list to load data in sheet when click Retrive
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierMgmtVO> searchCarrier(CarrierMgmtVO carrierMgmtVO)
			throws EventException {
		try {
			return dbDao.searchCarrier(carrierMgmtVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	/**
	 * Handle action insert, update, delete
	 * 
	 * @param CarrierMgmtVO[] carrierMgmtVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	@Override
	public void manageCarrier(CarrierMgmtVO[] carrierMgmtVO,
			SignOnUserAccount account) throws EventException {
		try {
			List<CarrierMgmtVO> insertVoList = new ArrayList<CarrierMgmtVO>();
			List<CarrierMgmtVO> updateVoList = new ArrayList<CarrierMgmtVO>();
			List<CarrierMgmtVO> deleteVoList = new ArrayList<CarrierMgmtVO>();
			List<CarrierMgmtVO> listDuplicated = new ArrayList<CarrierMgmtVO>();
			for ( int i=0; i<carrierMgmtVO .length; i++ ) {
				if ( carrierMgmtVO[i].getIbflag().equals("I")){ 
					carrierMgmtVO[i].setCreUsrId(account.getUsr_id());
					carrierMgmtVO[i].setUpdUsrId(account.getUsr_id());;
					insertVoList.add(carrierMgmtVO[i]);
				} else if ( carrierMgmtVO[i].getIbflag().equals("U")){
					carrierMgmtVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(carrierMgmtVO[i]);
				} else if ( carrierMgmtVO[i].getIbflag().equals("D")){
					deleteVoList.add(carrierMgmtVO[i]);
				}
			}
			
			if(insertVoList.size() > 0) {
				dbDao.addmanageCarrierS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.updateCarrierS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.deleteCarrierS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}

	/**
	 * Search Carrier List to validate check valid when insert and show dropdown list when init
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierMgmtVO> searchCarrierList(CarrierMgmtVO carrierMgmtVO)
			throws EventException {
		try {
			return dbDao.searchCarrierList(carrierMgmtVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	/**
	 * Search Rlane List to validate check valid when insert
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierMgmtVO> searchRlane(CarrierMgmtVO carrierMgmtVO)
			throws EventException {
		try {
			return dbDao.searchRLane(carrierMgmtVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	/**
	 * Search Trade List to validate check valid when insert and update
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierMgmtVO> searchTrade(CarrierMgmtVO carrierMgmtVO)
			throws EventException {
		try {
			return dbDao.searchTrade(carrierMgmtVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	/**
	 * Search Customer Code List to validate check valid when insert and update
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierMgmtVO> searchCusCode(CarrierMgmtVO carrierMgmtVO)
			throws EventException {
		try {
			return dbDao.searchCusCode(carrierMgmtVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	/**
	 * Search Vendor List to validate check valid when insert and update
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierMgmtVO> searchVendor(CarrierMgmtVO carrierMgmtVO)
			throws EventException {
		try {
			return dbDao.searchVendor(carrierMgmtVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

	/**
	 * Check duplicated joo carrier and rlane have exist below DB
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierMgmtVO> checkDuplicated(CarrierMgmtVO carrierMgmtVO)
			throws EventException {
		try {
			return dbDao.checkDupl(carrierMgmtVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler().getMessage(),ex);
		}
	}

}
