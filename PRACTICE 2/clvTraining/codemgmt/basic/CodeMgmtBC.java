package com.clt.apps.opus.esm.clvtraining.codemgmt.basic;

import java.util.List;

import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.esm.clvtraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface CodeMgmtBC {
	
	public List<CodeMgmtMasterVO> searchCodeMgmtMaster(CodeMgmtMasterVO codeMgmtMasterVO) throws EventException;
	
	public List<CodeMgmtDetailVO> searchCodeMgmtDetail(CodeMgmtDetailVO codeMgmtDetailVO) throws EventException;
	
	public void manageCodeMgmtMaster(CodeMgmtMasterVO[] codeMgmtMasterVO,SignOnUserAccount account) throws EventException;

	public void manageCodeMgmtDetail(CodeMgmtDetailVO[] codeMgmtDetailVO,SignOnUserAccount account) throws EventException;
}
