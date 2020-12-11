package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.login.domain.model.SearchForm;

/*Model「SearchForm」をセットしていないコントローラがあることにより、ページによってはアクセスできなくなってしまうので、
 * 各コントローラーの処理終了後に必要であればSearchFormをmodelAndViewに追加する
 */
public class CustomHandlerInterceptor implements HandlerInterceptor {



	//各コントローラー実行前にリクエストされているURIをコンソールに表示
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



		//リクエストされているURIをコンソールに表示
		System.out.println(request.getRequestURI());

		//リクエストをハンドラ(コントローラー)によってさらに処理をするので、trueを返す
		return true;


	}

	//各コントローラー実行後にSearchFormをmodelAndViewに追加する
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


	      //modelAndViewがnullでない、もしくはSearchFormが存在しなかった場合、空のフォームを追加する
        if(modelAndView != null && !modelAndView.getModel().containsKey("searchForm")) {
            modelAndView.addObject("searchForm", new SearchForm());
        }

	}
}
