package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.biz.cbt.dao.CbtDao;
import com.biz.cbt.db.OracleSqlFactory;
import com.biz.cbt.vo.CbtVO;

public class CbtService {

	SqlSessionFactory sessionFactory;
	Scanner scan;

	List<CbtVO> cbtList;

	public CbtService() {
		scan = new Scanner(System.in);
		OracleSqlFactory sqlFactory = new OracleSqlFactory();
		this.sessionFactory = sqlFactory.getSessionFactory();
		cbtList = new ArrayList();
	}

	public List<CbtVO> cbtView() {
		SqlSession session = this.sessionFactory.openSession();
		CbtDao dao = session.getMapper(CbtDao.class);

		List<CbtVO> cbtList = dao.selectAll();
		for (CbtVO vo : cbtList) {
			//System.out.println(vo);
		}
		return cbtList;
	}

	public CbtVO cb_FIND_NUM(String cb_num) {
		SqlSession session = this.sessionFactory.openSession();
		CbtDao dao = session.getMapper(CbtDao.class);

		CbtVO vo = dao.findByNum(cb_num);
		return vo;

	}
	

	public void menu() {

		while (true) {
			System.out.println("=====================================");
			System.out.println("1.문제입력   2.문제풀이   0.종료");
			System.out.println("-------------------------------------");
			System.out.print("선택 >> ");
			String selectN = scan.nextLine();
			int selectNum = Integer.valueOf(selectN);
			if (selectNum == 0)
				break;
			if (selectNum == 1)
				menu2();

			if (selectNum == 2)
				viewQue();
		}

	}
	

	/*
	 * menu에서 1.문제입력을 했을 때 나오는 화면
	 */
	public void menu2() {

		while (true) {
			System.out.println("=================================================");
			System.out.println("1.문제등록   2.문제수정   3.문제삭제   0.종료");
			System.out.println("-------------------------------------------------");
			System.out.print("선택 >> ");
			String selectN = scan.nextLine();
			int selectNum = Integer.valueOf(selectN);
			if (selectNum == 0)
				break;

			if (selectNum == 1)
				insert();
			if (selectNum == 2)
				update();
			if (selectNum == 3)
				delete();

		}
	}

	
	/*
	 * 문제 등록과 수정할 때 사용
	 */
	public CbtVO inPutData() {
		System.out.println("=================================================");

		System.out.print("번호입력 >> ");
		String stNum = scan.nextLine();

		System.out.print("문제입력 >> ");
		String stQue = scan.nextLine();

		System.out.print("답안 1번 >> ");
		String stEx1 = scan.nextLine();

		System.out.print("답안 2번 >> ");
		String stEx2 = scan.nextLine();

		System.out.print("답안 3번 >> ");
		String stEx3 = scan.nextLine();

		System.out.print("답안 4번 >> ");
		String stEx4 = scan.nextLine();

		System.out.print("정답 문항 >> ");
		String stAns = scan.nextLine();
		System.out.println("=================================================");
		System.out.println("※ 등록 되었습니다.");

		CbtVO vo = new CbtVO();
		vo.setCb_num(stNum);
		vo.setCb_que(stQue);
		vo.setCb_ex1(stEx1);
		vo.setCb_ex2(stEx2);
		vo.setCb_ex3(stEx3);
		vo.setCb_ex4(stEx4);
		vo.setCb_ans(stAns);

		return vo;

	}
	
	/*
	 * 문제 보여주고 정답 맞추기
	 */

	public void viewQue() {

		cbtList = cbtView();

		Collections.shuffle(cbtList);
		int index = 1;

		for (CbtVO vo : cbtList) {

			String[] str1 = vo.getCb_ex1().split("/");
			String strOX1 = str1[0];
			String strEx1 = str1[1];

			String[] str2 = vo.getCb_ex2().split("/");
			String strOX2 = str2[0];
			String strEx2 = str2[1];

			String[] str3 = vo.getCb_ex3().split("/");
			String strOX3 = str3[0];
			String strEx3 = str3[1];

			String[] str4 = vo.getCb_ex4().split("/");
			String strOX4 = str4[0];
			String strEx4 = str4[1];

			String[] strAns = vo.getCb_ans().split("/");
			String strExAns = strAns[1];

			String[] mixS = { strEx1, strEx2, strEx3, strEx4 };
			Collections.shuffle(Arrays.asList(mixS));

			System.out.println("================================");
			System.out.print(index + ". ");
			System.out.println(vo.getCb_que());
			System.out.println("--------------------------------");
			System.out.println("1. " + mixS[0]);
			System.out.println("2. " + mixS[1]);
			System.out.println("3. " + mixS[2]);
			System.out.println("4. " + mixS[3]);
			System.out.println("---------------------------------");
			System.out.print("정답>> ");
			String an = scan.nextLine();

			int intN = Integer.valueOf(an);
			// System.out.println("1" + strExAns);
			// System.out.println("2" + mixS[intN - 1]);

			if (mixS[intN - 1].equals(strExAns)) {
				System.out.println("정답!");

			} else {
				System.out.println("틀렸습니다.");
				System.out.println("1:다시풀기, Enter:다음문제풀기 >> ");
				String input = scan.nextLine();
				if (input.equals("1")) {
					System.out.println("================================");
					System.out.print(index + ". ");
					System.out.println(vo.getCb_que());
					System.out.println("--------------------------------");
					System.out.println("1. " + mixS[0]);
					System.out.println("2. " + mixS[1]);
					System.out.println("3. " + mixS[2]);
					System.out.println("4. " + mixS[3]);
					System.out.println("---------------------------------");
					System.out.print("정답>> ");
					String an1 = scan.nextLine();

					int intN1 = Integer.valueOf(an1);
					// System.out.println("1" + strExAns);
					// System.out.println("2" + mixS[intN - 1]);

					if (mixS[intN - 1].equals(strExAns)) {
						System.out.println("정답!");
					}else {
						System.out.println("틀렸습니다.");
					}

				}
			}

		}
		index++;

	}
	/*
	 * 문제 삭제
	 */

	public void delete() {

		System.out.print("번호입력(종료:0) >> ");
		String strNum = scan.nextLine();
		if (strNum.equals("0"))
			return;

		CbtVO cbtvo = this.cb_FIND_NUM(strNum);
		if (cbtvo == null)
			return;

		System.out.println(cbtvo);
		System.out.print("삭제할까요? YES/NO >> ");
		String strYN = scan.nextLine();

		if (strYN.equalsIgnoreCase("YES")) {
			SqlSession session = sessionFactory.openSession();
			CbtDao dao = session.getMapper(CbtDao.class);

			int ret = dao.delete(strNum);

			session.commit();
			session.close();

			if (ret > 0) {
				System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("삭제가 완료되지못하였습니다.");
			}
		}
	}

	/*
	 * 문제 등록
	 */
	public void insert() {

		CbtVO vo = inPutData();

		SqlSession session = sessionFactory.openSession();
		CbtDao dao = session.getMapper(CbtDao.class);

		int ret = dao.insert(vo);

		session.commit();
		session.close();

		if (ret > 0) {
			System.out.println("추가가 완료되었습니다.");
		} else {
			System.out.println("추가가 완료되지못하였습니다.");
		}
	}

	/*
	 * 문제 수정
	 */
	public void update() {

		CbtVO vo = inPutData();

		SqlSession session = sessionFactory.openSession();
		CbtDao dao = session.getMapper(CbtDao.class);

		int ret = dao.update(vo);

		session.commit();
		session.close();

		if (ret > 0) {
			System.out.println("변경이 완료되었습니다.");
		} else {
			System.out.println("변경이 완료되지못하였습니다.");
		}
	}

}
