package com.clt.apps.opus.esm.clvtraining.carriermgmt.basic;

import java.util.List;

import com.clt.apps.opus.esm.clvtraining.carriermgmt.vo.CarrierMgmtVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface CarrierMgmtBC {

	/**
	 * Search Joo Carrier list to load data in sheet when click Retrive
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	public List<CarrierMgmtVO> searchCarrier(CarrierMgmtVO carrierMgmtVO) throws EventException;
	
	/**
	 * Check duplicated joo carrier and rlane have exist below DB
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	public List<CarrierMgmtVO> checkDuplicated(CarrierMgmtVO carrierMgmtVO) throws EventException;
	
	/**
	 * Search Carrier List to validate check valid when insert and show dropdown list when init
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	public List<CarrierMgmtVO> searchCarrierList(CarrierMgmtVO carrierMgmtVO) throws EventException;
	
	/**
	 * Search Rlane List to validate check valid when insert
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	public List<CarrierMgmtVO> searchRlane(CarrierMgmtVO carrierMgmtVO) throws EventException;
	
	/**
	 * Search Trade List to validate check valid when insert and update
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	public List<CarrierMgmtVO> searchTrade(CarrierMgmtVO carrierMgmtVO) throws EventException;
	
	/**
	 * Search Customer Code List to validate check valid when insert and update
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	public List<CarrierMgmtVO> searchCusCode(CarrierMgmtVO carrierMgmtVO) throws EventException;
	
	/**
	 * Search Vendor List to validate check valid when insert and update
	 * 
	 * @param CarrierMgmtVO	carrierMgmtVO
	 * @return List<CarrierMgmtVO>
	 * @exception EventException
	 */
	public List<CarrierMgmtVO> searchVendor(CarrierMgmtVO carrierMgmtVO) throws EventException;
	
	/**
	 * Handle action insert, update, delete
	 * 
	 * @param CarrierMgmtVO[] carrierMgmtVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageCarrier(CarrierMgmtVO[] carrierMgmtVO,SignOnUserAccount account) throws EventException;
}
