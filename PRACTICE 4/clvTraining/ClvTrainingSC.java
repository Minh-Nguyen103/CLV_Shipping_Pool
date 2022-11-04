/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ClvTrainingSC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.10.15
*@LastModifier : 
*@LastVersion : 1.0
* 2022.10.15 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clvtraining;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clvtraining.carriermgmt.basic.CarrierMgmtBC;
import com.clt.apps.opus.esm.clvtraining.carriermgmt.basic.CarrierMgmtBCImpl;
import com.clt.apps.opus.esm.clvtraining.carriermgmt.event.NynPractice0004Event;
import com.clt.apps.opus.esm.clvtraining.carriermgmt.vo.CarrierMgmtVO;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.bizcommon.carrier.basic.CarrierBCImpl;



/**
 * ALPS-ClvTraining Business Logic ServiceCommand - Process the business transaction for ALPS-ClvTraining.
 * 
 * @author Nguyen
 * @see 
 * @since J2EE 1.6
 */

public class ClvTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	* ClvTraining system task scenario precedent work<br>
	* Creating related internal objects when calling a business scenario<br>
	*/
	
	public void doStart() {
		log.debug("ClvTrainingSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	* ClvTraining system work scenario finishing work<br>
	* Release related internal objects when the work scenario is finished<br>
	*/
	public void doEnd() {
		log.debug("ClvTrainingSC 종료");
	}

	/**
	* Carry out business scenarios for each event<br>
	* Branch processing of all events occurring in ALPS-ClvTraining system work<br>
	*
	* @param e Event
	* @return EventResponse
	* @exception EventException
	*/
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// The part to use when SC handles multiple events		
		if (e.getEventName().equalsIgnoreCase("NynPractice0004Event")) {
			if(e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initData(e);
			}
			
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchCarrier(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageCarrier(e);
			}else if(e.getFormCommand().isCommand(FormCommand.COMMAND05)){
				eventResponse = checkDuplicated(e);
			}else if(e.getFormCommand().isCommand(FormCommand.COMMAND01) || e.getFormCommand().isCommand(FormCommand.COMMAND02) ||
					e.getFormCommand().isCommand(FormCommand.COMMAND03) || e.getFormCommand().isCommand(FormCommand.COMMAND04)){
				eventResponse = checkValid(e);
			}
		}
		
	
		return eventResponse;
	}
	
	/**
	* Search JOO Carrier List
	*
	* @param Event e
	* @return EventResponse
	* @exception EventException
	*/
	public EventResponse searchCarrier(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		NynPractice0004Event event = (NynPractice0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try{
			begin();
			List<CarrierMgmtVO> list = command.searchCarrier(event.getCarrierMgmtVO());
			eventResponse.setRsVoList(list);
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
	/**
	* Managing JOO carrier list
	*
	* @param Event e
	* @return EventResponse
	* @exception EventException
	*/
	public EventResponse manageCarrier(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		NynPractice0004Event event = (NynPractice0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try{
			begin();
			command.manageCarrier(event.getCarrierMgmtVOs(), account);
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
	/**
	* Initial data
	*
	* @param Event e
	* @return EventResponse
	* @exception EventException
	*/
	public EventResponse initData(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		NynPractice0004Event event = (NynPractice0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try{
			begin();
			
			StringBuilder crrComboBuider = new StringBuilder("");
			List<CarrierMgmtVO> carrierList = command.searchCarrierList(event.getCarrierMgmtVO());
			if(carrierList != null && carrierList.size() > 0) {
				for (CarrierMgmtVO carrierMgmtVO : carrierList) {
					crrComboBuider.append(carrierMgmtVO.getJoCrrCd()+ "|");
				}
				String crrCombo = crrComboBuider.substring(0, crrComboBuider.length() - 1);
			}
			
			StringBuilder rlaneComboBuider = new StringBuilder("");
			List<CarrierMgmtVO> rlaneList = command.searchRlane(event.getCarrierMgmtVO());
			System.out.println("Dây roi..");
			System.out.println(rlaneList.size());
			if(rlaneList != null && rlaneList.size() > 0) {
				for (CarrierMgmtVO carrierMgmtVO : rlaneList) {
					rlaneComboBuider.append(carrierMgmtVO.getRlaneCd() + "|");
				}
				String rlaneCombo = rlaneComboBuider.substring(0, rlaneComboBuider.length() - 1);
			}
			eventResponse.setETCData("crrCombo", crrComboBuider.toString().substring(0, crrComboBuider.length() - 1));
			eventResponse.setETCData("rlaneCombo", rlaneComboBuider.toString().substring(0, rlaneComboBuider.length() - 1));
			
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
	/**
	* Check duplicated data
	*
	* @param Event e
	* @return EventResponse
	* @exception EventException
	*/
	public EventResponse checkDuplicated(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		NynPractice0004Event event = (NynPractice0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try{
			begin();
			List<CarrierMgmtVO> list = command.checkDuplicated( event.getCarrierMgmtVO());

			if(list.size() > 0) {
				eventResponse.setETCData("HAVECODE", "N");
			}else {
				eventResponse.setETCData("HAVECODE", "Y");
			}
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
	
	/**
	* Check validation data
	*
	* @param Event e
	* @return EventResponse
	* @exception EventException
	*/
	public EventResponse checkValid(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		NynPractice0004Event event = (NynPractice0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try{
			begin();
			CarrierMgmtVO carrierVO = event.getCarrierMgmtVO();
			List<CarrierMgmtVO> list = null;
			
			if(event.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				list = command.searchCarrierList(carrierVO);
			}else if(event.getFormCommand().isCommand(FormCommand.COMMAND02)) {
				list = command.searchVendor(carrierVO);
			}else if(event.getFormCommand().isCommand(FormCommand.COMMAND03)){
				list = command.searchCusCode(carrierVO);
			}else if(event.getFormCommand().isCommand(FormCommand.COMMAND04)){
				list = command.searchTrade(carrierVO);
			}else if(event.getFormCommand().isCommand(FormCommand.COMMAND05)){
				list = command.checkDuplicated(carrierVO);
			}

			if(list != null && list.size() > 0) {
				eventResponse.setETCData("HAVECODE", "Y");
			}else {
				eventResponse.setETCData("HAVECODE", "N");
			}
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	

}