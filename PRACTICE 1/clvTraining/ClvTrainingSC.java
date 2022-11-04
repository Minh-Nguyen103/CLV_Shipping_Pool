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


import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.event.NynPractice0001Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.vo.ErrMsgVO;
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
		if (e.getEventName().equalsIgnoreCase("NynPractice0001Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchErrMsg(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageErrMsg(e);
			}
		}
		
		return eventResponse;
	}
	/**
	* NYN_PRACTICE_0001 : Event<br>
	* [Act] for [Business Target].<br>
	*
	* @param Event e
	* @return EventResponse
	* @exception EventException
	*/
	private EventResponse searchErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		NynPractice0001Event event = (NynPractice0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();

		try{
			List<ErrMsgVO> list = command.searchErrMsg(event.getErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	
	/**
	* NYN_PRACTICE_0001 : [Event]<br>
	* [Act] for [Business Target].<br>
	*
	* @param Event e
	* @return EventResponse
	* @exception EventException
	*/
	private EventResponse manageErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		NynPractice0001Event event = (NynPractice0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		try{
			begin();
			command.manageErrMsg(event.getErrMsgVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
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