import java.util.*;

public class BlockChain {
	private Block head = new Block (); // start the chain with the Genesis block
	private Block tail = head;
	private String courseName = "NotEntered";
	
	public BlockChain ( String courseName) {
		this.courseName = new String (courseName);
	}
	
	public void printBlockChain() {
		Block temp = head;
		
		while (temp != null)
		{
			System.out.println(temp);
			temp = temp.next();
		}
	}
	
	public boolean verifyChain() {
		Block blocks = head;

		while (blocks.next() != null)
		{
			if (blocks.next().isEqual(blocks)) 
			{
				blocks = blocks.next();
			} 
			else {
				return false;
			}
		}
		return true;
	}
	
	public Block removeBadBlock() {
		Block currentBlock = head;
		Block previousBlock = head;

		while (currentBlock.next() != null)
		{
			if (currentBlock.next() == null) 
			{
				return null;
			} else {
				previousBlock = currentBlock;
				currentBlock = currentBlock.next();				
			}
			if (!currentBlock.isEqual(previousBlock))
			{			
				previousBlock.updateNext(null);
			}
//			if (!currentBlock.next().isEqual(previousBlock)) // if hashCode are not matched, 
		}
//		if (!verifyChain()) {
//			previousBlock.updateNext(null);
//		}
		// after i remove them, i can't add blocks anymore why.
		return currentBlock;
	}
	
	
	
	public void addBlock(Scanner keyboard) {
		Block newOne = new Block();
		
		newOne.addInfoToBlock(keyboard, tail.getCurrentHash());
		tail.updateNext(newOne);
		tail = newOne;
	}
	
	public void addBadBlock(Scanner keyboard) {
		Random random = new Random();
		Block newOne = new Block();
		if (newOne.addInfoToBlock(keyboard, random.nextFloat())){
			// add to chain at tail
			tail.updateNext(newOne);
			tail = newOne;			
		}
		
	}
}
