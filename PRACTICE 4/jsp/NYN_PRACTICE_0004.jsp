<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : NYN_PRACTICE_0004.jsp
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.10.31
*@LastModifier : 
*@LastVersion : 1.0
* 2022.10.31
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.esm.clvtraining.carriermgmt.event.NynPractice0004Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	NynPractice0004Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	String crrComboCd = "";
	String rlaneComboCd = "";
	Logger log = Logger.getLogger("com.clt.apps.ClvTraining.CarrierMgmt");

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (NynPractice0004Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		crrComboCd = eventResponse.getETCData("crrCombo");
		rlaneComboCd = eventResponse.getETCData("rlaneCombo");

	}catch(Exception e) {
		out.println(e.toString());
	}
%>

<html>
<head>
<title>Code Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript">
	var crrCombo = "All|<%=crrComboCd%>";
	var rlaneCombo = "|<%=rlaneComboCd%>";
	function setupPage(){
		loadPage();
	}

</script>
</head>

<body  onLoad="setupPage();">
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
<!-- 개발자 작업	-->
		<div class="page_title_area clear">
		<h2 class="page_title"><button type="button"><span id="title"></span></button></h2>
		<div class="opus_design_btn">
		   <button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
		   --><button type="button" class="btn_accent" name="btn_New" id="btn_New">New</button><!--
		   --><button type="button" class="btn_accent" name="btn_Save" id="btn_Save">Save</button><!-- 
		    --><button type="button" class="btn_accent" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button>
		</div>
		       <div class="location">
		        <span id="navigation"></span>
		       </div>
		</div>
		
		<div class="wrap_search">
			<div class="opus_design_inquiry wFit">
			    <table>
			        <tbody>
						<tr>
						<th width="70">Carrier</th>
						<td width="100"><script type="text/javascript">ComComboObject('s_jo_crr_cd',1,80,1)</script></td>
						
						<th width="70">Vendor</th>
						<td width="100"><input type="text" style="width:100px;" class="input" value="" name="s_vendor" id="s_vendor"></td>
						
						<th width="70">Create Date</th>
						<td>
						<input type="text" style="width:80px;" class="input" value="" name="s_cre_date_from" id="s_cre_date_from" dataformat="ymd" maxlength="10" minlength="8"><!-- 
						 --><button type="button" class="calendar ir" name="btns_calendar1" id="btns_calendar1"></button>~
						<input type="text" style="width:80px;" class="input" value="" name="s_cre_date_to" id="s_cre_date_to" dataformat="ymd" maxlength="10" minlength="8" ><!-- 
						 --><button type="button" class="calendar ir" name="btns_calendar2" id="btns_calendar2"></button>
						</td>
						</tr> 
			</tbody>
			</table>
			</div>
		</div>
		
		<div class="wrap_result">
			<div class="opus_design_grid">
				<div class="opus_design_btn">
					<button type="button" class="btn_normal" name="btn_delete" id="btn_delete">Row Delete</button><!--
				 	--><button type="button" class="btn_normal" name="btn_add" id="btn_add" ">Row Add</button>
					
				</div>			
			</div>
			<script language="javascript">ComSheetObject('sheet1');</script>
		</div>


<!-- 개발자 작업  끝 -->
</form>
</body>
</html>