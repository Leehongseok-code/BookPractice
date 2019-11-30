import java.util.Scanner;
import java.util.*;

class oper
{
	double add(double n1,double n2)
	{
		return n1+n2;
	}
	double mns(double n1,double n2)
	{
		return n1-n2;
	}
	double time(double n1,double n2)
	{
		return n1*n2;
	}
	double devide(double n1,double n2)
	{
		return n1/n2;
	}
	void push(Vector a)
	{
		;
	}
	float numpop(Vector<Float> a)//벡터에서 숫자를 pop해서 반환하는 함수
	{
		float num;
		num=0;
		if(a.size()>0)
		{
			//System.out.println("Q");
			num=a.get(a.size()-1);
			a.remove(a.size()-1);
		}
		return num; 
	}
	boolean isnum(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			if(Character.isDigit(str.charAt(i))==true)
			{
				
			}
			else return false;
		}
		return true;
	}
	float doop(float a,float b,String op)
	{
		if(op.equals("+"))
			return a+b;
		else if(op.equals("-"))
			return b-a;
		else if(op.equals("*"))
			return a*b;
		else if(op.equals("/"))
			return b/a;
		return 0;
	}
}

public class Sclass extends oper
{
	
	public static void main(String[] args) 
	{
		Sclass s=new Sclass();
		
		Vector<String> stk_str=new Vector<String>();//후위계산식을 받는 벡터
		Vector<Integer> stk_int=new Vector<Integer>();
		Vector<String> stk_op=new Vector<String>();//후위계산식으로 바꾸면서, 여산자들을 담아놓는 벡터
		Vector<Float> stk_last=new Vector<Float>();//후위계산식을 이용해 실제로 계산값을 도출하여 담아놓는 벡터
		HashMap<String,Integer> oprank=new HashMap<String,Integer>();//연산자들의 우선순위를 담는 해쉬 맵(딕셔너리)
		// TODO Auto-generated method stub
		String str="";//스캐너에서 입력받은 연산자를 임시로 저장하는 공간
		oprank.put("+",1);
		oprank.put("-",1);
		oprank.put("*",2);
		oprank.put("/",2);
		oprank.put("(",0);
		Scanner scanner=new Scanner(System.in);
		System.out.println("계산식(숫자는 정수만 가능)을 공백으로 구분하여 입력하세요");
		System.out.println("ex)3 + 2 * 5 =");
		
		
		
		
		//계산하는 파트
		//중위연산식을 후위연산식으로 변경
		while(str.equals("=")==false)
		{
			str=scanner.next();
			if(str.equals("="))
				break;
			if((s.isnum(str)==true))
			{
				stk_str.add(str);//피연산자는 그대로 출력
			}
			else//연산자일 때 스택에 담아놓고 출력하는 과정
			{
				if(str.equals("("))
				{
					stk_op.add(str);
				}
				else if(str.equals(")"))//닫는괄호를 만나면 여는괄호를 만날때까지 연산자를 모두 pop
				{
					//int i=stk_op.size()-1;
					while((stk_op.get(stk_op.size()-1)).equals("("));
					{
						
						stk_str.add(stk_op.get(stk_op.size()-1));
						stk_op.remove(stk_op.size()-1);
						
					}
					//System.out.println(stk_op.get(stk_op.size()-1));
					stk_op.remove(stk_op.size()-1);//모두 제거하고나면 '('이 남아있으므로 한 번 더 pop해서 제거해준다.
				}
				else if(stk_op.isEmpty()==true||oprank.get(str)>oprank.get(stk_op.get(stk_op.size()-1)))//연산자를 만나면 우서순위에 따라 모두 pop하여 출력벡터에 담음
				{
					stk_op.add(str);
				}
				else
				{
					//연산자 스택에 우선순위가 더 크거나 같은 것이 있을 경우 스택에서 pop해서 출력하고 연산자를 push
					stk_str.add(stk_op.get(stk_op.size()-1));//연산자스택에서 pop하는 과정
					stk_op.remove(stk_op.size()-1);//연산자스택에서 pop하는 과정
					stk_op.add(str);//후위계산식에 출력
				}
			}
		}
		while(stk_op.isEmpty()==false)
		{
			stk_str.add(stk_op.get(stk_op.size()-1));
			stk_op.remove(stk_op.size()-1);
		}
		Iterator<String>it=stk_str.iterator();//string iterator
		/*while(it.hasNext()==true)
		{
			//System.out.println(it.next());
		}*/
		it=stk_str.iterator();
		
		//후위연산식을 바탕으로 스택 계산과정
		while(it.hasNext()==true)
		{
			str=it.next();
			if(s.isnum(str)==true)
				stk_last.add(Float.parseFloat(str));
			else
			{
				stk_last.add(s.doop(s.numpop(stk_last),s.numpop(stk_last),str));
			}
			//System.out.println(stk_last.get(stk_last.size()-1));
		}
		System.out.println(stk_last.get(0));
		
	}

}
