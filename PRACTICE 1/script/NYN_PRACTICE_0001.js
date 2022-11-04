/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : NYN_PRACTICE_0001.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.10.15
*@LastModifier : 
*@LastVersion : 1.0
* 2022.10.15 
* 1.0 Creation
=========================================================*/
/****************************************************************************************
  이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
					[수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
					기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
/**
 * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
 * @author 한진해운
 */

/**
 * @extends
 * @class NYN_PRACTICE_0001 : NYN_PRACTICE_0001 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
 */
function NYN_PRACTICE_0001() {
  this.processButtonClick = tprocessButtonClick;
  this.setSheetObject = setSheetObject;
  this.loadPage = loadPage;
  this.initSheet = initSheet;
  this.initControl = initControl;
  this.doActionIBSheet = doActionIBSheet;
  this.setTabObject = setTabObject;
  this.validateForm = validateForm;
}

/* 개발자 작업	*/

var sheetObjects = new Array();
var sheetCnt = 0;
let rowCount = 0;
let flagFirstInsert = false;
let indexRowInsert = 0;
let reminderIbflag;
let arrMsgCd = [];
let flagActivitys = false;
let countChange = 0;

document.onclick=processButtonClick;

function setSheetObject(sheet_obj) {
  sheetObjects[sheetCnt++] = sheet_obj;
}

function loadPage() {
  for (var i = 0; i < sheetObjects.length; i++) {
    ComConfigSheet(sheetObjects[i]);
    initSheet(sheetObjects[i], i + 1);
    ComEndConfigSheet(sheetObjects[i]);
  }
  doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

function initSheet(sheetObj, sheetNo) {
  var cnt = 0;
  switch (sheetNo) {
    case 1:
      with (sheetObj) {
    	 var HeadTitle = "|Del|Msg Cd|Msg Type|Msg level|Message|Description";

         SetConfig({SearchMode : 2, MergeSheet : 5, Page : 1, DataRowMerge : 0});

         var info = { Sort: 1, ColMove: 1, HeaderCheck: 0, ColResize: 1 };
         var headers = [{ Text: HeadTitle, Align: "Center" }];
         InitHeaders(headers, info);

         var cols = [
           {Type: "Status",Hidden: 0,Width: 30,Align: "Center",ColMerge: 0,SaveName: "ibflag"},
           {Type: "DelCheck",Hidden: 0,Width: 45,Align: "Center",ColMerge: 1,SaveName: "DEL",keyField: 0,CalcLogic: "",Format: "",PointCount: 0,UpdateEdit: 1,InsertEdit: 1},
           {Type: "Text",Hidden: 0,Width: 80,Align: "Center",ColMerge: 0,SaveName: "err_msg_cd",keyField: 1,CalcLogic: "",Format: "",PointCount: 0,UpdateEdit: 0,InsertEdit: 1},
           {Type: "Combo",Hidden: 0,Width: 80,Align: "Center",ColMerge: 0,SaveName: "err_tp_cd",keyField: 1,CalcLogic: "",Format: "",PointCount: 0,UpdateEdit: 1,InsertEdit: 1,ComboText: "Server|UI|Both",ComboCode: "S|U|B"},
           {Type: "Combo",Hidden: 0,Width: 80,Align: "Center",ColMerge: 0,SaveName: "err_lvl_cd",keyField: 1,CalcLogic: "",Format: "",PointCount: 0,UpdateEdit: 1,InsertEdit: 1,ComboText: "ERR|WARNING|INFO",ComboCode: "E|W|I"},
           {Type: "Text",Hidden: 0,Width: 400,Align: "Left",ColMerge: 1,SaveName: "err_msg", keyField: 1,CalcLogic: "",Format: "",PointCount: 0,UpdateEdit: 1,InsertEdit: 1,MultiLineText: 1},
           {Type: "Text",Hidden: 0,Width: 250,Align: "Left",ColMerge: 0,SaveName: "err_desc",keyField: 0,CalcLogic: "",Format: "",PointCount: 0,UpdateEdit: 1,InsertEdit: 1}
         ];

         InitColumns(cols);
         SetWaitImageVisible(0);    
         resizeSheet();
         
    }
       
      break;

  }
}

function resizeSheet() {
  ComResizeSheet(sheetObjects[0]);
}

function processButtonClick() {
  var sheetObject1 = sheetObjects[0];

  var formObject = document.form;
  
  try {
    var srcName = ComGetEvent("name");
  
    switch (srcName) {
      case "btn_Add":
        doActionIBSheet(sheetObject1, formObject, IBINSERT);
        break;
      case "btn_Retrieve":
        doActionIBSheet(sheetObject1, formObject, IBSEARCH);
        break;
      case "btn_Save":
        doActionIBSheet(sheetObject1, formObject, IBSAVE);
        break;
      case "btn_DownExcel":
        doActionIBSheet(sheetObject1, formObject, IBDOWNEXCEL);
        break;
    }
  } catch (e) {
	  ComShowMessage(e);
  }
}

  function doActionIBSheet(sheetObj, formObj, sAction) {
    switch (sAction) {
      case IBSEARCH:
        ComOpenWait(true);
        formObj.f_cmd.value = SEARCH;
        sheetObj.DoSearch("NYN_PRACTICE_0001GS.do", FormQueryString(formObj));
        break;
      case IBSAVE:  
        formObj.f_cmd.value = MULTI;
        const idxRow = sheetObj.GetSelectionRows();
        reminderIbflag = sheetObj.GetCellValue(idxRow, 0);
        sheetObj.DoSave("NYN_PRACTICE_0001GS.do", FormQueryString(formObj));
        break;
      case IBINSERT:  	 
    	      sheetObj.DataInsert();
        break;
      case IBDOWNEXCEL:	
        if (sheetObj.RowCount() < 1) {
          ComShowCodeMessage("COM132501");
        } else {
          sheetObj.Down2Excel({
            DownCols: makeHiddenSkipCol(sheetObj),
            SheetDesign: 1,
            Merge: 1,
          });
        }
        break;

      default:
        break;
    }
  }
  
  function sheet1_OnSaveEnd(StMsg, Code, Msg, StCode){
	  if(Code >= 0) {
		  ComShowCodeMessage("COM132601");
//		  switch (reminderIbflag) {
//		case 'I':
//			ComShowCodeMessage("COM132601");
//			break;
//			
//		case 'U':
//			ComShowMessage("Update Successfully!!");
//				break;
//				
//		case 'D':
//			ComShowMessage("Delete Successfully!!");
//			break;
//		case 'ALL':
//			ComShowMessage("Tất cả thành công");
//
//		default:
//			break;
//		}
		  
		  
	  }else {
		   arrMsgCd = [];
	  }
  }
  
  function sheet1_OnValidation(Value,Row,Col){  
	  console.log(Row);
	  const err_value = sheet1.GetCellValue(Row,Col);
	  const ibflag = sheet1.GetCellValue(Row,0);
	  
	  switch (Col) {  
			case 2:
				if(ibflag === "U" || ibflag === "D") break;
				
				console.log(err_value);
				if(err_value === "" ){
					sheet1.ValidateFail(1);
					ComShowCodeMessage("COM130201", "msg cd")
					break;
					
				}
				
				if(err_value.length > 8) {
					sheet1.ValidateFail(1);
					ComShowCodeMessage("COM12142", "Msg cd", "8")
					break;
					
				}
				
				if(arrMsgCd.includes(err_value) ) {
					sheet1.ValidateFail(1);
					ComShowMessage("Can not multi row add that Msg_cd duplicated");
					arrMsgCd = [];
					
				}else {
					arrMsgCd.push(err_value);
				}
					break;
			
			case 5:
			
				if(ibflag === "D") break;
				
				if(ibflag === "U" && err_value === "") {
					sheet1.ValidateFail(1);
					ComShowCodeMessage("COM130201", "message");
					arrMsgCd = [];
					break;
				}
		
				if(err_value === ""  ){
					sheet1.ValidateFail(1)
					ComShowCodeMessage("COM130201", "message");
					arrMsgCd = [];
				}
				break;
		
			default:
				break;
			}
	
  }
  
  function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
   	ComOpenWait(false);
   }


/* 개발자 작업  끝 */
