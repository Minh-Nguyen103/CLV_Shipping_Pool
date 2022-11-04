let sheetObjectList = new Array();
let sheetCount = 0;
let cdIDSearch = "";
let flagToggleQues = true;
let flagSuccess = false;
let typeActivity = '';
document.onclick = proccessButtonClick;

function setSheetObject(sheetObj){
	
	sheetObjectList[sheetCount++] = sheetObj;
	console.log(sheetCount);
}

function loadPage() {
	for (let i = 0; i < sheetObjectList.length; i++) {
		ComConfigSheet(sheetObjectList[i]);
		initSheet(sheetObjectList[i], i +1)
		ComEndConfigSheet(sheetObjectList[i]);
	}
	
	doActionIBSheet(sheetObjectList[0], document.form, IBSEARCH);
	
}

function initSheet(sheetObj, sheetNo) {
	switch (sheetNo) {
	case 1:
		with(sheetObj) {
			const headerTitle = "|SubSystem|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date";
			SetConfig({SearchMode : 2, Page : 20});
			
			const info = {Sort: 1, ColMove: 1, ColResize: 1};
			const headers = [{Text: headerTitle, Align: "Center"}];
			InitHeaders(headers, info);
			
			const cols = [{Type: "Status",Hidden: 0, Width:30, Align:"Center",SaveName: "ibflag", ColMerge: 0},
			              {Type: "Combo", Width:70, Align:"Center",SaveName: "owr_sub_sys_cd", ColMerge: 1, KeyField:0, InsertEdit: 1, UpdateEdit: 1 },
			              {Type: "Text", Width:60, Align:"Center",SaveName: "intg_cd_id", KeyField:1, InsertEdit: 1, UpdateEdit: 0 },
			              {Type: "Text", Width:200, Align:"Left",SaveName: "intg_cd_nm", KeyField:0, InsertEdit: 1, UpdateEdit: 1 },
			              {Type: "Text", Width:50, Align:"Center",SaveName: "intg_cd_len", KeyField:0, InsertEdit: 1, UpdateEdit: 1 },
			              {Type: "Combo", Width:100, Align:"Center",SaveName: "intg_cd_tp_cd", KeyField:0, InsertEdit: 1, UpdateEdit: 1, ComboText: "General Code|Table Code",ComboCode: "G|T" },
			              {Type: "Text", Width:150, Align:"Left",SaveName: "mng_tbl_nm", KeyField:0, InsertEdit: 1, UpdateEdit: 0},
			              {Type: "Text", Width:350, Align:"Left",SaveName: "intg_cd_desc", KeyField:0, InsertEdit: 1, UpdateEdit: 1},
			              {Type: "Text", Width: 40, Align:"Center",SaveName: "intg_cd_use_flg", KeyField:0, InsertEdit: 1, UpdateEdit: 1 },
			              {Type: "Text", Width:80, Align:"Center",SaveName: "cre_usr_id", KeyField:0,InsertEdit: 0, UpdateEdit: 0 },
			              {Type: "Text", Width:80, Align:"Center",SaveName: "cre_dt", KeyField:0, Format:"Ymd", InsertEdit: 0, UpdateEdit: 0 },
			              {Type: "Text", Width:80, Align:"Center",SaveName: "upd_usr_id", KeyField:0, InsertEdit: 0, UpdateEdit: 0 },
			              {Type: "Text", Width:80, Align:"Center",SaveName: "upd_dt", KeyField:0,Format:"Ymd", InsertEdit: 0, UpdateEdit: 0 }];
			
			InitColumns(cols);
			resizeSheet(sheetObj);
			
//			SetEditable(1);
            SetSheetHeight(240);
		
	}
		break;
		
	case 2:
		with(sheetObj) {
			const headerTitle = "|Cd ID|Cd Val|Display Name|Description Remark|Order";
			SetConfig({SearchMode : 2, Page : 20});
			
			const info = {Sort: 1, ColMove: 1, ColResize: 1};
			const headers = [{Text: headerTitle, Align: "Center"}];
			InitHeaders(headers, info);
			
			const cols = [{Type: "Status",Hidden: 1, Width:10, Align:"Center",SaveName: "ibflag", ColMerge: 0, KeyField:1, UpdateEdit:1,   InsertEdit:1},
			              {Type:"Text", Hidden:0, Width:60,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_id",KeyField:1, UpdateEdit:0,   InsertEdit:0 },
			              {Type: "Text", Width:60, Align:"Center",SaveName: "intg_cd_val_ctnt", KeyField:1 , UpdateEdit:0,   InsertEdit:1 },
			              {Type: "Text", Width:200, Align:"Center",SaveName: "intg_cd_val_dp_desc", KeyField:0,   UpdateEdit:1,   InsertEdit:1 },
			              {Type: "Text", Width:600, Align:"Left",SaveName: "intg_cd_val_desc", KeyField:0, UpdateEdit:1,   InsertEdit:1 },
			              {Type: "Text", Width:50, Align:"Center",SaveName: "intg_cd_val_dp_seq", KeyField:0,   UpdateEdit:1,   InsertEdit:1 },
			              ];
			
			InitColumns(cols);
			resizeSheet(sheetObj);
		}
		break;

	default:
		break;
	}
	
}

function sheet1_OnDblClick(Value, Row, Col, CellX, CellY,
		CellW, CellH) {
	const sheetObject2 = sheetObjectList[1];
	const formObject = document.form;
	const cdIdElement = document.getElementById('s_cd_id');
	const valueToSearch = sheet1.GetCellValue(Row, 2);
	const isInsert = sheet1.GetCellValue(Row, 0) === "I";

	if(cdIdElement && valueToSearch && !isInsert) {
		cdIdElement.value = sheet1.GetCellValue(Row, 2);
		doActionIBSheet(sheetObject2, formObject, "IBSEARCH01");
	}else {
//		ComShowCodeMessage("COM130401");
	}
	
}

function resizeSheet(sheetObj){
	ComResizeSheet(sheetObj);
}


function proccessButtonClick(e) {
	 const sheetObject1 = sheetObjectList[0];
	 const sheetObject2 = sheetObjectList[1];
	  var formObject = document.form;
	  
	  try {
	    var srcName = ComGetEvent("name");
	  
	    switch (srcName) {
	      case "btn_Retrieve":
	        doActionIBSheet(sheetObject1, formObject, IBSEARCH);
	        break;
	        
	      case "btn_Save":
	    	   const countStatusSheet1 = sheetObject1.RowCount("I")+sheetObject1.RowCount("U")+sheetObject1.RowCount("D");
	    	   const countStatusSheet2 = sheetObject2.RowCount("I")+sheetObject2.RowCount("U")+sheetObject2.RowCount("D");
	    	   if(countStatusSheet1 && countStatusSheet2) flagToggleQues = false;
	    	   
	    	   if(countStatusSheet1 > 0){
	        		doActionIBSheet(sheetObject1,formObject,IBSAVE);	 	    	  
	    	   } 	
	    	   
	    	   if(countStatusSheet2 > 0 && flagToggleQues) {
	    		   console.log("vo luon ne")
//	    		   if(countStatusSheet1 && countStatusSheet2 && flagSuccess) flagToggleQues = false;
	        		doActionIBSheet(sheetObject2,formObject,IBSAVE);
	    	   }
	    	   
	    	   if(countStatusSheet1 + countStatusSheet2 === 0) {
	    		   ComShowMessage("No data to save")
	    	   }
	        	 
	        break;
	        
	      case "btn_add_mst":
		        doActionIBSheet(sheetObject1, formObject, IBINSERT);
		        break;
		        
		  case "btn_delete_mst":
			    doActionIBSheet(sheetObject1, formObject, 'MODIFY1');
			    break;
			    
		  case "btn_add_dtl":
			  const rowSelect = sheetObject1.GetSelectionRows();
			  if(rowSelect) {
				    doActionIBSheet(sheetObject2, formObject, IBINSERT);
			  }else {
				  ComShowCodeMessage("COM12189");
			  }
			    break;
			        
		  case "btn_delete_dtl":
			  console.log("vao chua nay")
			  doActionIBSheet(sheetObject2, formObject, 'MODIFY2');
				break;

	    }
	  } catch (e) {
		  ComShowMessage(e);
	  }
	
}



function doActionIBSheet(sheetObj, formObj, sAction, haveQues = 1) {
	 switch (sAction) {
     case IBSEARCH:
       ComOpenWait(true);
       formObj.f_cmd.value = SEARCH;
 	  console.log("cool")
       sheetObj.DoSearch("NYN_PRACTICE_0002GS.do", FormQueryString(formObj));
       break;
       
     case "IBSEARCH01":
         ComOpenWait(true);
         formObj.f_cmd.value = SEARCH01;
         sheetObj.DoSearch("NYN_PRACTICE_0002GS.do", FormQueryString(formObj));
         break;
     case IBINSERT:  	 
    	 with(sheetObj) {
    	 
    	 if(sheetObj.id === 'sheet1') {
    		 DataInsert(-1);
    		 sheetObjectList[1].RemoveAll();
    	 }
    	 if(sheetObj.id === 'sheet2') {
    		 const cdIDValue = sheetObjectList[0].GetCellValue(sheetObjectList[0].GetSelectionRows(), "intg_cd_id");
    		 console.log(cdIDValue)
    		 if(cdIDValue){
    			 DataInsert(-1);
    			 SetCellValue(sheetObj.LastRow(), "intg_cd_id", cdIDValue);
//    			 if(sheetObj.SearchRows() == 0) {
//        			 SetCellValue(sheetObj.LastRow(), "intg_cd_id", cdIDValue);
//        		 }else {
//         		 	 SetCellValue(sheetObj.LastRow(), "intg_cd_id", sheetObj.GetCellValue(1, "intg_cd_id"));
//        		 }
    		 }else {
    			 ComShowMessage("Nothing CdId");
    		 }		
    	 }
     }
    	 
	      
	     break;    
     case IBSAVE:  
    	 	if(sheetObj.id === 'sheet1') {
    	 		 formObj.f_cmd.value = MULTI01;
    	         sheetObj.DoSave("NYN_PRACTICE_0002GS.do", FormQueryString(formObj), -1, haveQues);
    	 	}
			if(sheetObj.id === 'sheet2') {
				formObj.f_cmd.value = MULTI02;
				console.log(haveQues);
				sheetObj.DoSave("NYN_PRACTICE_0002GS.do", FormQueryString(formObj),-1, haveQues);
			
        
    	 }
       
       break;
       
     case 'MODIFY1': // Row Delete
     	const rowSheet1=sheetObj.GetSelectRow();
     	sheetObj.SetCellValue(rowSheet1,"ibflag","D");
     	const haveRemove = confirm("Do you want to Save?");

     	console.log(haveRemove);
     	if(!haveRemove) break;
     	flagSuccess = sheetObjectList[1].RowCount() > 0 ? true : false
     	typeActivity = "DELETE";
        doActionIBSheet(sheetObj, document.form, IBSAVE, false);
     		
     	if( sheetObjectList[1].RowCount()> 0){
     		   for(i=sheetObjectList[1].LastRow(); i > 0; i--){
     			   	sheetObjectList[1].SetCellValue(i, "ibflag","D");
     		   }
     		   doActionIBSheet(sheetObjectList[1], document.form, IBSAVE, false);
     		}
     	
	 	    break; 
     case 'MODIFY2': // Row Delete
    	 console.log("vao chua")
      	const rowSheet2=sheetObj.GetSelectRow();
      	sheetObj.SetCellValue(rowSheet2,"ibflag","D");
      	doActionIBSheet(sheetObj, document.form, IBSAVE);

 	 	    break; 

     default:
       break;
   }
}

function sheet1_OnSaveEnd(StMsg, Code, Msg, StCode){
	  if(Code >= 0 ) {
		  if(flagToggleQues) {
			  console.log("sheet1 xong")
			  console.log(flagSuccess,typeActivity)

			  if(flagSuccess && typeActivity === "DELETE") {
				  flagSuccess = false;
				  return;
			  }else {
				  flagSuccess = true;
			  }
			  ComShowCodeMessage("COM132601");  
			  typeActivity = "";
		  }else {
			  console.log("sheet1 xong nhưng false")
			  doActionIBSheet(sheetObjectList[1],document.form,IBSAVE, flagToggleQues);
		  }
	
	  }
}

function sheet2_OnSaveEnd(StMsg, Code, Msg, StCode){
	  if(Code >= 0) {
		  console.log("sheet2 xong")
		  flagToggleQues = true;
		  if(flagSuccess && typeActivity === "DELETE") {
			  flagSuccess = false;
			 return;
		  }else {
			  flagSuccess = true;
		  }
		  ComShowCodeMessage("COM132601");
		  typeActivity = "";
	  }
}

function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
   	ComOpenWait(false);
   }

function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
	const cdIdElement = document.getElementById('s_cd_id');
	 if(cdIdElement) cdIdElement.value = ""

   	ComOpenWait(false);
   }


	




