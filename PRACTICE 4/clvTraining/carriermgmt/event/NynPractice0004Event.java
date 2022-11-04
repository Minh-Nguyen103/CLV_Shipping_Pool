package com.clt.apps.opus.esm.clvtraining.carriermgmt.event;

import com.clt.apps.opus.esm.clvtraining.carriermgmt.vo.CarrierMgmtVO;
import com.clt.framework.support.layer.event.EventSupport;

public class NynPractice0004Event extends EventSupport {
	
private static final long serialVersionUID = 1L;
	
	/** Table Value Object */
	CarrierMgmtVO carrierMgmtVO = null;
	
	/** Table Value Object Multi Data */
	CarrierMgmtVO[] carrierMgmtVOs = null;
	
	public NynPractice0004Event(){}

	public CarrierMgmtVO getCarrierMgmtVO() {
		return carrierMgmtVO;
	}

	public void setCarrierMgmtVO(CarrierMgmtVO carrierMgmtVO) {
		this.carrierMgmtVO = carrierMgmtVO;
	}

	public CarrierMgmtVO[] getCarrierMgmtVOs() {
		return carrierMgmtVOs;
	}

	public void setCarrierMgmtVOs(CarrierMgmtVO[] carrierMgmtVOs) {
		this.carrierMgmtVOs = carrierMgmtVOs;
	}


	
	

}
