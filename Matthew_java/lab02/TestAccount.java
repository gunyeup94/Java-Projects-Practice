public class TestAccount{
	public static void main(String[] args){
		Account kathy = new Account(100);
		Account megan = new Account(10, kathy);
		System.out.println(megan.parentAccount.balance);
		if(megan.withdraw(50)){
			System.out.print(megan.balance);
		} else {
			System.out.print("work harder");
		}
	}
}