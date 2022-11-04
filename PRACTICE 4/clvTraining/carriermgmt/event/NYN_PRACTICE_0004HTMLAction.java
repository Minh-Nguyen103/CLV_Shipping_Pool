package com.clt.apps.opus.esm.clvtraining.carriermgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clvtraining.carriermgmt.event.NynPractice0004Event;
import com.clt.apps.opus.esm.clvtraining.carriermgmt.vo.CarrierMgmtVO;
import com.clt.apps.opus.esm.clvtraining.errmsgmgmt.vo.ErrMsgVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class NYN_PRACTICE_0004HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	* Create a NYN_PRACTICE_0001HTMLAction object
	*/
	public NYN_PRACTICE_0004HTMLAction() {}

	/**
	* Parsing the HTML DOM object's Value as a Java variable<br>
	* Parsing the information of HttpRequst as ClvTrainingEvent and setting it in the request<br>
	* @param request HttpServletRequest HttpRequest
	* @return Event An object that implements the Event interface.
	* @exception HTMLActionException
	*/
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		NynPractice0004Event event = new NynPractice0004Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setCarrierMgmtVOs((CarrierMgmtVO[])getVOs(request, CarrierMgmtVO.class, ""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			CarrierMgmtVO carrierMgmtVO = new CarrierMgmtVO();
			carrierMgmtVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			carrierMgmtVO.setVndrSeq(JSPUtil.getParameter(request, "s_vendor", ""));
			carrierMgmtVO.setCreDtFrom(JSPUtil.getParameter(request, "s_cre_date_from", ""));
			carrierMgmtVO.setCreDtTo(JSPUtil.getParameter(request, "s_cre_date_to", ""));
			event.setCarrierMgmtVO(carrierMgmtVO);			
		}else if(command.isCommand(FormCommand.COMMAND01) || command.isCommand(FormCommand.COMMAND02) || command.isCommand(FormCommand.COMMAND03) ||
				command.isCommand(FormCommand.COMMAND04) || command.isCommand(FormCommand.COMMAND05)){
			event.setCarrierMgmtVO((CarrierMgmtVO)getVO(request, CarrierMgmtVO.class,""));
		}

		return  event;
	}

	/**
	* Saving the value of the task scenario execution result in the attribute of HttpRequest<br>
	* Setting the ResultSet that transmits the execution result from the ServiceCommand to the View (JSP) in the request<br>
	*
	* @param request HttpServletRequest HttpRequest
	* @param eventResponse An object that implements the EventResponse interface.
	*/
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	* Save HttpRequest parsing result value in HttpRequest attribute<br>
	* HttpRequest parsing result value set in request<br>
	*
	* @param request HttpServletRequest HttpRequest
	* @param event An object that implements the Event interface.
	*/
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}
