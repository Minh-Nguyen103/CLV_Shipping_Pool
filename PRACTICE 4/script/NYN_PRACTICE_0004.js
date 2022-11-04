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

let sheetObjectList = new Array();
let sheetCount = 0;
let comboObjectList = new Array();
let comboCount = 0;
//Event handler processing by button click event */
document.onclick = proccessButtonClick;

//Put sheet objects in global variable "sheetObjectList"
function setSheetObject(sheetObj){
	sheetObjectList[sheetCount++] = sheetObj;
}

//Put combo objects in global variable "comboObjectList"
function setComboObject(comboObj){
	comboObjectList[comboCount++] = comboObj;
}

//Function sets the default settings of the sheet and the combo
//It is the first called area when fire jsp onload event
function loadPage() {
	for (let i = 0; i < sheetObjectList.length; i++) {
		ComConfigSheet(sheetObjectList[i]);
		initSheet(sheetObjectList[i], i +1);
		ComEndConfigSheet(sheetObjectList[i]);
	}

	for (let i = 0; i < comboObjectList.length; i++) {
		initCombo(comboObjectList[i], i +1)
	}
	
	doActionIBSheet(sheetObjectList[0], document.form, IBSEARCH);
	
}

//Function that define the basic properties of the combo on the screen
//For example Combo item, combo basic attributes, etc
function initCombo(comboObj, comboNo) {
	switch (comboNo) {
	case 1:
		with (comboObj) {
		SetMultiSelect(1);
        SetDropHeight(200);
	}
	var comboItems = crrCombo.split("|");
	addComboItem(comboObj, comboItems);
	comboObj.SetSelectIndex(0);
	break;

	}
}

//Add item to combo
function addComboItem(comboObj, comboItems) {
	for (let i = 0; i < comboItems.length; i++) {
		const comboItem = comboItems[i];
		comboObj.InsertItem(i, comboItem, comboItem);
	}
}

//Function that define the basic properties of the sheet on the screen
//For example Columns information, sheet basic attributes, etc
function initSheet(sheetObj, sheetNo) {
	switch (sheetNo) {
	case 1:
		with(sheetObj) {
			const headerTitle = "STS|Chk|Carrier|Rev . Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
			SetConfig({SearchMode : 2, MergeSheet : 5, Page : 20});
			
			const info = {Sort: 1, ColMove: 1,HeaderCheck:0, ColResize: 1};
			const headers = [{Text: headerTitle, Align: "Center"}];
			InitHeaders(headers, info);
			
			const cols = [{Type: "Status",Hidden: 0, Width:50, Align:"Center",SaveName: "ibflag", ColMerge: 0},
			              {Type: "DelCheck", Width:50, Align:"Center",SaveName: "del_chk", ColMerge: 1, KeyField:0, InsertEdit: 1, UpdateEdit: 1 },
			              {Type: "Text", Width:70, Align:"Center",SaveName: "jo_crr_cd", KeyField:1, InsertEdit: 1, UpdateEdit: 0, EditLen: 3},
			              {Type: "Combo", Width:100, Align:"Center",SaveName: "rlane_cd", KeyField:1, InsertEdit: 1, UpdateEdit: 0, ComboText:rlaneCombo , ComboCode: rlaneCombo },
			              {Type: "Text", Width:100, Align:"Center",SaveName: "vndr_seq", KeyField:1, InsertEdit: 1, UpdateEdit: 1, EditLen:6 },
			              {Type: "Text", Width:50, Align:"Center",SaveName: "cust_cnt_cd", ColMerge: 0, KeyField:1, InsertEdit: 1, UpdateEdit: 1, EditLen: 2 },
			              {Type: "Text", Width:100, Align:"Center",SaveName: "cust_seq", ColMerge: 0, KeyField:1, InsertEdit: 1, UpdateEdit: 1, EditLen: 6 },
			              {Type: "Text", Width:70, Align:"Center",SaveName: "trd_cd", KeyField:0, InsertEdit: 1, UpdateEdit: 1, EditLen:3 },
			              {Type: "Combo", Width:70, Align:"Center",SaveName: "delt_flg", KeyField:0, InsertEdit: 1, UpdateEdit: 1, ComboText:"N|Y" , ComboCode: "N|Y" },
			              {Type: "Text", Width:150, Align:"Center",SaveName: "cre_dt", KeyField:0, Format:"", InsertEdit: 0, UpdateEdit: 0 },
			              {Type: "Text", Width:180, Align:"Left",SaveName: "cre_usr_id", KeyField:0,InsertEdit: 0, UpdateEdit: 0 },
			              {Type: "Text", Width:150, Align:"Center",SaveName: "upd_dt", KeyField:0,Format:"", InsertEdit: 0, UpdateEdit: 0 },
			              {Type: "Text", Width:180, Align:"Left",SaveName: "upd_usr_id", KeyField:0, InsertEdit: 0, UpdateEdit: 0 }];
			
			InitColumns(cols);
			SetColProperty('jo_crr_cd', {AcceptKeys: 'N|E', InputCaseSensitive: 1});
			SetColProperty('rlane_cd', {AcceptKeys: 'N|E', InputCaseSensitive: 1});
			SetColProperty('vndr_seq', {AcceptKeys: 'N'});
			SetColProperty('cust_cnt_cd', {AcceptKeys: 'E', InputCaseSensitive: 1});
			SetColProperty('cust_seq', {AcceptKeys: 'N'});
			SetColProperty('trd_cd', {AcceptKeys: 'E', InputCaseSensitive: 1});
			SetColProperty('trd_cd', {AcceptKeys: 'E', InputCaseSensitive: 1});
			resizeSheet(sheetObj);
		
	}
		break;
	default:
		break;
	}
	
}

//Handle click when combo item be clicked
//If item "All" have been check, all rest item will unchecked
//If any item except item "All" have been check, item "All" will unchecked
//If all item haven't been check, item "All" will checked
function s_jo_crr_cd_OnCheckClick(Checked, Index, Code) {
	const comboObj1 = comboObjectList[0];
	
	if(Index === 0) {
		const valueCode = comboObj1.GetItemCheck(Index);
		if(valueCode) {
			for (var i = 1; i < comboObj1.GetItemCount(); i++) {
				comboObj1.SetItemCheck(i, false);
			}
		}
	}else {
		comboObj1.SetItemCheck(0, false);
	}
	
	let checkCount = 0;
	for (var i = 1; i < comboObj1.GetItemCount(); i++) {
		if(comboObj1.GetItemCheck(i)){
			checkCount++;
		}
	}
	
	if(checkCount === 0) {
		comboObj1.SetItemCheck(0,true);
	}
	
}

//Check data exist have change before do action save
function checkChangedDataExist(sheetObj) {
	const countStatusSheetObj = sheetObj.RowCount("I")+sheetObj.RowCount("U")+sheetObj.RowCount("D");
	
	return countStatusSheetObj > 0 ? true : false;
}

function resizeSheet(sheetObj){
	ComResizeSheet(sheetObj);
}

//Event handler processing by button name */
function proccessButtonClick(e) {
	 const sheetObject1 = sheetObjectList[0];
	  var formObject = document.form;
	  
	  try {
	    var srcName = ComGetEvent("name");
	  
	    switch (srcName) {
	      case "btn_Retrieve":
	        doActionIBSheet(sheetObject1, formObject, IBSEARCH);
	        break;
	        
	      case "btn_Save":
	    	  if(checkChangedDataExist(sheetObject1)){
	    		  doActionIBSheet(sheetObject1, formObject, IBSAVE); 
	    	  }else {
	    		  ComShowMessage("No change data found.");
	    	  }
	    	        	 
	        break;
	        
	      case "btn_add":
		        doActionIBSheet(sheetObject1, formObject, IBINSERT);
		        break;
		        
		  case "btn_delete":
			    doActionIBSheet(sheetObject1, formObject, 'DELETE');
			    break;
			    
		  case "btn_DownExcel":
			    doActionIBSheet(sheetObject1, formObject, IBDOWNEXCEL);
			    break;
			    
		  case "btn_New":
			  sheetObject1.RemoveAll();
			  break;
			  
		  case "btns_calendar1":
		  case "btns_calendar2":
			  const calendar = new ComCalendar();
			  if(srcName === "btns_calendar1"){
				  calendar.select(formObject.s_cre_date_from, 'yyyy-MM-dd');
              }else{
            	  calendar.select(formObject.s_cre_date_to, 'yyyy-MM-dd');
              }
			  break;

	    }
	  } catch (e) {
		  ComShowMessage(e);
	  }
	
}


//Define transaction logic between UI and Server
function doActionIBSheet(sheetObj, formObj, sAction, haveQues = 1) {
	 switch (sAction) {
     case IBSEARCH:
    	 if(!checkValidDate(sheetObj, formObj)) return;
	       ComOpenWait(true);
	       formObj.f_cmd.value = SEARCH;
	       sheetObj.DoSearch("NYN_PRACTICE_0004GS.do", FormQueryString(formObj));
	       break;
       
     case IBINSERT:  	 
    	 	sheetObj.DataInsert(-1);
	     	break;    
	     	
     case IBSAVE:  
  	 		 formObj.f_cmd.value = MULTI;
    	     sheetObj.DoSave("NYN_PRACTICE_0004GS.do", FormQueryString(formObj), -1, haveQues);  
    	     
    	     break;
     case 'DELETE': // Row Delete    	 
	     	const haveRemove = confirm("Do you want to remove row?");
	     	if(!haveRemove) break;
	     	
	     	const rowSheet=sheetObj.GetSelectRow();
	     	sheetObj.SetCellValue(rowSheet,"ibflag","D");
	     	
	     	doActionIBSheet(sheetObj, document.form, IBSAVE, false);   	
	 	    break; 
	 	    
     case IBDOWNEXCEL: 
	    	 if (sheetObj.RowCount() < 1) {
	             ComShowCodeMessage("COM132501");
	           } else {
	             sheetObj.Down2Excel({
	               DownCols: 'jo_crr_cd|rlane_cd|vndr_seq|cust_cnt_cd|cust_seq|trd_cd|delt_flg|cre_dt|cre_usr_id|upd_dt|upd_usr_id',
	               SheetDesign: 1,
	             });
	           }
	    	 break;

     default:
       break;
   }
}

/*Handle Check valid date with requirements:
 * Is the format YYYY-MM_DD correct?
 * Create date from must less than create date to
 * */

function checkValidDate(sheetObj, formObj) {
	const creDtFrom = formObj.s_cre_date_from.value;
	const creDtTo = formObj.s_cre_date_to.value;

	if(creDtFrom !== "" && creDtTo !== "" && creDtFrom > creDtTo) {
		ComShowMessage("Create Date From must less than Create Date To!");
		return false;
	}
	
	if(!ComChkObjValid(formObj.s_cre_date_from)) return false;
	if(!ComChkObjValid(formObj.s_cre_date_to)) return false;
	
	return true;
}

//Handle sheet1 when on change. Validation fields below server when editing completing
function sheet1_OnChange(RaiseFlag,Row,Col,Value,OldValue){
	const sheetObject1 = sheetObjectList[0];
	const formObject = document.form;
	const colName = sheetObject1.ColSaveName(Col);
	
	if(Value === '') return;
	
	if(colName === 'jo_crr_cd') {
		formObject.f_cmd.value = COMMAND01
		const param = FormQueryString(formObject) + "&&jo_crr_cd="+ Value;
		
		const sXml = sheetObject1.GetSearchData("NYN_PRACTICE_0004GS.do", param);
		const haveCode = ComGetEtcData(sXml, "HAVECODE");
		if(haveCode === "N") {
			ComShowCodeMessage("COM132201", Value);
			sheetObject1.SetCellValue(Row, Col, OldValue,0);
		}
	}else if(colName === 'vndr_seq') {
		formObject.f_cmd.value = COMMAND02
		const param = FormQueryString(formObject) + "&&vndr_seq="+ Value;
		
		const sXml = sheetObject1.GetSearchData("NYN_PRACTICE_0004GS.do", param);
		const haveCode = ComGetEtcData(sXml, "HAVECODE");
		if(haveCode === "N") {
			ComShowCodeMessage("COM132201", Value);
			sheetObject1.SetCellValue(Row, Col, OldValue,0);
		}
	}else if(colName === 'trd_cd') {
		formObject.f_cmd.value = COMMAND04
		const param = FormQueryString(formObject) + "&&trd_cd="+ Value;
		
		const sXml = sheetObject1.GetSearchData("NYN_PRACTICE_0004GS.do", param);
		const haveCode = ComGetEtcData(sXml, "HAVECODE");
		if(haveCode === "N") {
			ComShowCodeMessage("COM132201", Value);
			sheetObject1.SetCellValue(Row, Col, OldValue,0);
		}
	}
		
	 if(colName === 'jo_crr_cd' || colName === 'rlane_cd') {
		if(sheetObject1.GetCellValue(Row, 'jo_crr_cd') !== '' && sheetObject1.GetCellValue(Row, 'rlane_cd') !== ''){
		
			const headerRow = sheetObject1.HeaderRows();
			console.log(headerRow)
			console.log(sheetObject1.RowCount())
			for (let i = headerRow; i <= sheetObject1.RowCount(); i++) {

				if(Row !== i && sheetObject1.GetCellValue(Row, 'jo_crr_cd') === sheetObject1.GetCellValue(i, 'jo_crr_cd') &&
					sheetObject1.GetCellValue(Row, 'rlane_cd') === sheetObject1.GetCellValue(i, 'rlane_cd')){
					ComShowMessage("It have been duplicated. Please check the data!");
					sheetObject1.SetCellValue(Row, Col, OldValue,0);
					return;
				}
			}
			
			formObject.f_cmd.value = COMMAND05
			const param = FormQueryString(formObject) + "&&jo_crr_cd="+ sheetObject1.GetCellValue(Row, 'jo_crr_cd')
							+ "&&rlane_cd="+sheetObject1.GetCellValue(Row, 'rlane_cd');
					
			const sXml = sheetObject1.GetSearchData("NYN_PRACTICE_0004GS.do", param);
			const haveCode = ComGetEtcData(sXml, "HAVECODE");
			if(haveCode === "N") {
				ComShowMessage("It have been duplicated. Please check the data!");
				sheetObject1.SetCellValue(Row, Col, OldValue,0);
			}
		}
			
	 }
}

function sheet1_OnSaveEnd(StMsg, Code, Msg, StCode){
	  if(Code >= 0 ) {
		  ComShowCodeMessage("COM132601");
	  }
}

function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
   	ComOpenWait(false);
   }




	




