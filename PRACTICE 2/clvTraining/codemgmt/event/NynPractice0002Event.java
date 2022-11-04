package com.clt.apps.opus.esm.clvtraining.codemgmt.event;

import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.framework.support.layer.event.EventSupport;

public class NynPractice0002Event extends EventSupport{
	private static final long serialVersionUID = 1L;
	
	CodeMgmtMasterVO codeMgmtMasterVO = null;
	CodeMgmtMasterVO[] codeMgmtMasterVOList = null;
	
	CodeMgmtDetailVO codeMgmtDetailVO = null;
	CodeMgmtDetailVO[] codeMgmtDetailVOList = null;
	
	public CodeMgmtDetailVO getCodeMgmtDetailVO() {
		return codeMgmtDetailVO;
	}

	public void setCodeMgmtDetailVO(CodeMgmtDetailVO codeMgmtDetailVO) {
		this.codeMgmtDetailVO = codeMgmtDetailVO;
	}

	public CodeMgmtDetailVO[] getCodeMgmtDetailVOList() {
		return codeMgmtDetailVOList;
	}

	public void setCodeMgmtDetailVOList(CodeMgmtDetailVO[] codeMgmtDetailVOList) {
		this.codeMgmtDetailVOList = codeMgmtDetailVOList;
	}

	public NynPractice0002Event() {
	}
	
	public CodeMgmtMasterVO getCodeMgmtMasterVO() {
		return codeMgmtMasterVO;
	}
	public void setCodeMgmtMasterVO(CodeMgmtMasterVO codeMgmtMasterVO) {
		this.codeMgmtMasterVO = codeMgmtMasterVO;
	}
	public CodeMgmtMasterVO[] getCodeMgmtMasterVOList() {
		return codeMgmtMasterVOList;
	}
	public void setCodeMgmtMasterVOList(CodeMgmtMasterVO[] codeMgmtMasterVOList) {
		this.codeMgmtMasterVOList = codeMgmtMasterVOList;
	}
	
	
	
	
	
	
	

}
