import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import milech.poker.CardHistogram;
import milech.poker.Hand;
import milech.poker.HandConfig;
import milech.poker.HistEvaluator;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public Hello() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("user");
		Hand hand = null;
		try {
			hand = new Hand(name);
		} catch(Exception e) {
			out.println("" + e);
			hand = null;
		}
		if(hand != null) {
			CardHistogram cardHistogram = new CardHistogram(hand);
			HistEvaluator histEvaluator = new HistEvaluator();
			
			HandConfig handConfig = histEvaluator.evalConfType(cardHistogram, hand);
			
	//		out.println("hand: " + hand.toString());
	//		out.println("histogram: " + cardHistogram.toString());
		out.println("evaluation: " + handConfig);
		}
		
		out.close();
	}

}
