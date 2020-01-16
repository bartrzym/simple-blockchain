import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class BlockChain {

    public static int difficulty = 5;
    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {
        blockchain.add(new Block("First Block data", "0"));
        System.out.println("Mining block 1... ");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second block data",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 2... ");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block data",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 3... ");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}

