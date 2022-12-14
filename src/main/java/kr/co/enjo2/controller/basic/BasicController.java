package kr.co.enjo2.controller.basic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enjo2.action.Action;
import kr.co.enjo2.action.ActionForward;
import kr.co.enjo2.service.member.ChartService;
import kr.co.enjo2.service.notice.NoticeWrite;
import kr.co.enjo2.service.qna.QnaContentEditOkService;
import kr.co.enjo2.service.qna.QnaContentEditViewService;
import kr.co.enjo2.service.qna.QnaContentService;
import kr.co.enjo2.service.qna.QnaDeleteOkService;
import kr.co.enjo2.service.qna.QnaListService;
import kr.co.enjo2.service.qna.QnaReplyOkService;
import kr.co.enjo2.service.qna.QnaReplyViewService;
import kr.co.enjo2.service.qna.QnaViewService;
import kr.co.enjo2.service.qna.QnaWriteService;
import kr.co.enjo2.service.basic.FlightListService;
import kr.co.enjo2.service.basic.MainPageInfoService;
import kr.co.enjo2.service.member.JoinViewService;
import kr.co.enjo2.service.member.ManagementService;
import kr.co.enjo2.service.member.MemberEditOkService;
import kr.co.enjo2.service.member.MemberEditService;
import kr.co.enjo2.service.member.MemberLoginOkService;
import kr.co.enjo2.service.member.MemberLoginViewService;
import kr.co.enjo2.service.member.MemberLogoutService;
import kr.co.enjo2.service.member.MemberRegisterOkService;
import kr.co.enjo2.service.member.MemberUnregisterOkService;
import kr.co.enjo2.service.member.MemberUnregisterService;
import kr.co.enjo2.service.member.MyFlightService;
import kr.co.enjo2.service.member.MyMeetingService;
import kr.co.enjo2.service.member.MyMenuService;
import kr.co.enjo2.service.member.NickNameCheckService;
import kr.co.enjo2.service.notice.NoticeAddService;
import kr.co.enjo2.service.notice.NoticeContentService;
import kr.co.enjo2.service.notice.NoticeDeleteOkService;
import kr.co.enjo2.service.notice.NoticeEditOkService;
import kr.co.enjo2.service.notice.NoticeEditService;
import kr.co.enjo2.service.notice.NoticeListService;
import kr.co.enjo2.service.notice.NoticeWrite;

@WebServlet({ "/BasicController", "*.do" })
public class BasicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BasicController() {}
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response, String HttpMethodType) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("/loginView.do")) {
    		// ????????? ????????? ??????
    		action = new MemberLoginViewService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/loginOk.do")) {
    		// ????????? ?????? (????????? ??????)
    		action = new MemberLoginOkService();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/logout.do")) {
    		// ???????????? ??????
    		action = new MemberLogoutService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/joinView.do")) {
    		// ???????????? ????????? ??????
    		action = new JoinViewService();
    		forward = action.execute(request, response);
    	}  else if(url_Command.equals("/joinOk.do")) {
    		// ???????????? ?????? (???????????? ??????)
    		action = new MemberRegisterOkService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/myMenu.do")) {
    		// ?????? ??????
    		action = new MyMenuService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/myMeeting.do")) {
    		// ?????? ?????? ??????
    		action = new MyMeetingService();
    		forward = action.execute(request, response);
    	}else if(url_Command.equals("/myFlight.do")) {
    		// ?????? ?????? ??????
    		action = new MyFlightService();
    		forward = action.execute(request, response);
    	}else if(url_Command.equals("/management.do")) {
    		// ????????????
    		action = new ManagementService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/memberEdit.do")) {
    		// ???????????? ?????? ????????? ??????
    		action = new MemberEditService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/memberEditOk.do")) {
    		// ???????????? ?????? ??????
    		action = new MemberEditOkService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/memberUnregister.do")) {
    		// ???????????? ????????? ??????
    		action = new MemberUnregisterService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/memberUnregisterOk.do")) {
    		// ???????????? ??????
    		action = new MemberUnregisterOkService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/noticeWrite.do")) {
    		// ????????? ?????????
    		action = new NoticeWrite();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/noticeWriteOk.do")) {
    		//???????????? ?????????
    		action = new NoticeAddService();
    		forward = action.execute(request, response);
		} else if(url_Command.equals("/noticeList.do")) {
    		// ???????????? ??????, ??????, ?????? ????????? ?????? ??????
    		action = new NoticeListService();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/checkNickname.do")) {
    		// ????????? ??????
    		action = new NickNameCheckService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("/noticeContent.do")) {
            //???????????? ??? ?????? 
            action = new NoticeContentService();
            forward = action.execute(request, response); 
         } else if(url_Command.equals("/noticeEdit.do")) {
             // ???????????? ????????? ????????????
             action = new NoticeEditService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/noticeEditOk.do")) {
             // ???????????? ????????? ?????? ????????????
             action = new NoticeEditOkService();
             forward = action.execute(request, response);
             // noticeDelete.do
         } else if(url_Command.equals("/noticeDelete.do")) {
             // ???????????? ????????? ?????? ????????????
             action = new NoticeDeleteOkService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/chart.do")) {
             // ???????????? ????????? ?????? ????????????
             action = new ChartService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaList.do")) {
             // QNA (??????) ????????? JSON ??????
             action = new QnaListService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaWriteView.do")) {
             // QNA ?????? ????????? ??????
             action = new QnaViewService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaWriteOk.do")) {
             // QNA ?????? ??????
             action = new QnaWriteService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaContent.do")) {
             // ?????? QNA ??????
             action = new QnaContentService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaEdit.do")) {
             // ?????? QNA ?????? ??????
             action = new QnaContentEditViewService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaEditOk.do")) {
             // ?????? QNA ?????? ??????
             action = new QnaContentEditOkService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaDelete.do")) {
             // ?????? QNA ?????? ??????
             action = new QnaDeleteOkService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/mainInfo.do")) {
             // ?????? ????????? ????????? ?????? ??????
             action = new MainPageInfoService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/flightList.do")) {
          // ?????????????????? ??????, ??????, ?????? ????????? ?????? ??????(????????? ??????)
          action = new FlightListService();
          forward = action.execute(request, response);
        } else if(url_Command.equals("/qnaReplyView.do")) {
             // QnA ?????? ????????? ??????
             action = new QnaReplyViewService();
             forward = action.execute(request, response);
         } else if(url_Command.equals("/qnaReplyWriteOk.do")) {
             // QnA ?????? ??????
             action = new QnaReplyOkService();
             forward = action.execute(request, response);
         }
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		} else { //false (?????? ?????? ) ??????
    			//UI
    			//UI + ??????
    			//forward url ?????? ?????? ?????? View ????????? ?????? ??? ??????
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "GET");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "POST");
	}

}
