package com.clt.apps.opus.esm.clvtraining.codemgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class NYN_PRACTICE_0002HTMLAction extends HTMLActionSupport {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		NynPractice0002Event event = new NynPractice0002Event();
		
		if(command.isCommand(FormCommand.SEARCH)) {
			CodeMgmtMasterVO codeMgmtMasterVO = new CodeMgmtMasterVO();
			codeMgmtMasterVO.setIntgCdId(JSPUtil.getParameter(request, "s_cd_id", ""));
			codeMgmtMasterVO.setOwnrSubSysCd(JSPUtil.getParameter(request, "s_sub_system", ""));
			event.setCodeMgmtMasterVO(codeMgmtMasterVO);
		}else if(command.isCommand(FormCommand.SEARCH01)) {
			CodeMgmtDetailVO codeMgmtDetailVO = new CodeMgmtDetailVO();
			codeMgmtDetailVO.setIntgCdId(JSPUtil.getParameter(request, "s_cd_id", ""));
			event.setCodeMgmtDetailVO(codeMgmtDetailVO);
		}else if(command.isCommand(FormCommand.MULTI01)) {
			event.setCodeMgmtMasterVOList((CodeMgmtMasterVO[])getVOs(request, CodeMgmtMasterVO.class, ""));
//			String cdValue = JSPUtil.getParameter(request, "intg_cd_val_ctnt", "");
//			if("".equals(cdValue)) {
//				event.setCodeMgmtMasterVOList((CodeMgmtMasterVO[])getVOs(request, CodeMgmtMasterVO.class, ""));
//			}else {
//				event.setCodeMgmtDetailVOList((CodeMgmtDetailVO[])getVOs(request, CodeMgmtDetailVO.class, ""));
//			}
		}else if(command.isCommand(FormCommand.MULTI02)) {
			event.setCodeMgmtDetailVOList((CodeMgmtDetailVO[])getVOs(request, CodeMgmtDetailVO.class, ""));
		}
		return event;
	}
	
	
	/**
	 * HttpRequest??? attribute??? ?????????????????? ???????????? ??? ??????<br>
	 * ServiceCommand?????? View(JSP)??? ??????????????? ???????????? ResultSet??? request??? ??????<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface??? ????????? ??????
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest??? attribute??? HttpRequest ?????? ???????????? ??? ??????<br>
	 * HttpRequest ?????? ???????????? ??? request??? ??????<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface??? ????????? ??????
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}

}
